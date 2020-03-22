package com.revolut.currencies

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CurrenciesPresenter(
    private val view: CurrenciesContract.View,
    private val orchestrator: CurrenciesContract.Orchestrator,
    coroutineScope: CoroutineScope
) : CurrenciesContract.Presenter, CoroutineScope by coroutineScope {

    private var currencies: MutableList<CurrencyModel>? = null

    override fun init() {
        view.init(this)
        launch {
            currencies = orchestrator.getCurrencies().toMutableList()
            withContext(Dispatchers.Main) {
                view.updateData()
            }
        }
    }

    override fun bindItem(itemView: CurrenciesContract.ItemView, position: Int) {
        currencies?.apply {
            itemView.setCurrencyCode(get(position).base)
            itemView.setCurrencyAmount(get(position).amount)
        }
    }

    override fun onCurrencyClicked(position: Int) {
        currencies?.let {
            Collections.swap(it, position, 0)
            view.notifyItemMovedOnTop(position)
        }
    }

    override val currenciesCount
        get() = currencies?.count() ?: 0

}