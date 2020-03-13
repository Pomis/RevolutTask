package com.revolut.currencies

import kotlinx.coroutines.CoroutineScope

class CurrenciesPresenter(
    private val view: CurrenciesContract.View,
    private val orchestrator: CurrenciesContract.Orchestrator,
    coroutineScope: CoroutineScope
) : CurrenciesContract.Presenter, CoroutineScope by coroutineScope {

    override fun init() {
        view.init(this)
    }

}