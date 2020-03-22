package com.revolut.currencies

import kotlinx.coroutines.*
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

class CurrenciesPresenter(
    private val view: CurrenciesContract.View,
    private val orchestrator: CurrenciesContract.Orchestrator,
    coroutineScope: CoroutineScope
) : CurrenciesContract.Presenter, CoroutineScope by coroutineScope {

    private var latestRates: Map<String, Float>? = null
    private var model: MutableList<CurrencyModel>? = null
    private var selectedCurrency: CurrencyModel = CurrenciesDefaultConfig.DefaultCurrency

    override fun init() {
        view.init(this)
        launch {
            subscribeOnRateUpdates()
        }
    }

    private fun subscribeOnRateUpdates() {
        launch {
            while (isActive) {
                val rates = withContext(Dispatchers.IO) {
                    orchestrator.getLatestRates()
                }
                if (rates.isEmpty()) {
                    view.showError()
                }
                latestRates = rates
                updateModel(rates)
                delay(CurrenciesDefaultConfig.RefreshTimeoutMillis)
            }
        }
    }

    private suspend fun updateModel(rates: Map<String, Float>) {
        model?.forEachIndexed { index, it ->
            if (it.currencyCode != selectedCurrency.currencyCode) {
                it.amount =
                    (rates.getValue(it.currencyCode) / rates.getValue(
                        selectedCurrency.currencyCode
                    )) * selectedCurrency.amount
                withContext(Dispatchers.Main) {
                    view.updateItem(index)
                }
            }
        } ?: run {
            model = rates.map { CurrencyModel(it.key, it.value) }.toList().toMutableList()
        }
    }

    override fun bindItem(itemView: CurrenciesContract.ItemView, position: Int) {
        model?.apply {
            itemView.setCurrencyCode(get(position).currencyCode)
            itemView.setCurrencyAmount(get(position).amount)
        }
    }

    override fun onCurrencyClicked(position: Int) {
        model?.let {
            Collections.swap(it, position, 0)
            selectedCurrency = it[0]
            view.notifyItemMovedOnTop(position)
        }
    }

    override fun onBaseCurrencyAmountEdited(newValue: String?) {
        val format = NumberFormat.getInstance(Locale.getDefault())
        val amount = if (newValue != null) {
            val number = try { format.parse(newValue.toString()) } catch (e: ParseException) { 0 }
            number?.toFloat() ?: 0f
        } else {
            0f
        }
        selectedCurrency.amount = amount
        launch {
            latestRates?.let { updateModel(it) }
        }
    }

    override val currenciesCount
        get() = model?.count() ?: 0

}