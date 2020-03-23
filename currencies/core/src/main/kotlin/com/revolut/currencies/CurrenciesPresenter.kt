package com.revolut.currencies

import com.revolut.common.CommonContract
import com.revolut.common.toFloatSafe
import kotlinx.coroutines.*
import java.util.*

class CurrenciesPresenter(
    private val view: CurrenciesContract.View,
    private val orchestrator: CurrenciesContract.Orchestrator,
    coroutineScope: CommonContract.Scope
) : CurrenciesContract.Presenter, CommonContract.Scope by coroutineScope {

    private var latestRates: Map<String, Float>? = null
    private var model: MutableList<CurrencyModel>? = null
    private var base: CurrencyModel = CurrenciesDefaultConfig.DefaultCurrency

    override fun init() {
        view.init(this)
        launch {
            subscribeOnRateUpdates()
        }
    }

    private fun subscribeOnRateUpdates() {
        launch {
            while (isActive) {
                val rates = io { orchestrator.getLatestRates() }
                if (rates.isEmpty()) {
                    view.showError()
                }
                latestRates = rates
                io { updateModel(rates) }
                delay(CurrenciesDefaultConfig.RefreshTimeoutMillis)
            }
        }
    }

    private suspend fun updateModel(rates: Map<String, Float>) {
        val currentModel = model
        if (currentModel != null && currentModel.isNotEmpty()) {
            currentModel.forEachIndexed { index, it ->
                if (it.currencyCode != base.currencyCode) {
                    val exchangeRate = rates.getValue(it.currencyCode) / rates.getValue(base.currencyCode)
                    it.amount = exchangeRate * base.amount
                    ui { view.updateItem(index) }
                }
            }
            ui { view.hideError() }
        } else {
            model = rates.map { CurrencyModel(it.key, it.value) }.toList().toMutableList()
        }
    }

    override fun bindItem(itemView: CurrenciesContract.ItemView, position: Int) {

        model?.apply {
            val currencyModel = get(position)
            val displayName = Currency.getAvailableCurrencies().find {
                it.currencyCode == currencyModel.currencyCode
            }?.displayName

            itemView.setCurrencyCode(currencyModel.currencyCode)
            displayName?.let { itemView.setCurrencyName(it) }
            itemView.setCurrencyAmount(currencyModel.amount)
        }
    }

    override fun onCurrencyClicked(itemView: CurrenciesContract.ItemView, position: Int) {
        if (position != 0) {
            model?.let {
                Collections.swap(it, position, 0)
                base = it[0]
                view.notifyItemMovedOnTop(position)
                itemView.startEditing()
            }
        }
    }

    override fun onBaseCurrencyAmountEdited(newValue: String?) {
        base.amount = newValue.toFloatSafe()
        launch {
            latestRates?.let { updateModel(it) }
        }
    }

    override val currenciesCount
        get() = model?.count() ?: 0

}