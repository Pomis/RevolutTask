package com.revolut.currencies

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrenciesPresenter(
    private val view: CurrenciesContract.View,
    private val orchestrator: CurrenciesContract.Orchestrator,
    coroutineScope: CoroutineScope
) : CurrenciesContract.Presenter, CoroutineScope by coroutineScope {

    private var currencies: List<CurrencyModel>? = null

    override fun init() {
        view.init(this)
        launch {
            currencies = orchestrator.getCurrencies()
            withContext(Dispatchers.Main) {
                view.updateData()
            }
        }
    }

    override fun bindItem(itemView: CurrenciesContract.ItemView, position: Int) {
        currencies?.apply {
            itemView.setCurrencyCode(get(position).base)
            itemView.setCurrencyName(get(position).base)
//            view.updateData()
        }
    }

    override val currenciesCount
        get() = currencies?.count() ?: 0

}