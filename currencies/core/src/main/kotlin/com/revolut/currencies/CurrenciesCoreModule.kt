package com.revolut.currencies

import com.revolut.currencies.CurrenciesContract.Companion.MODULE_CURRENCIES_CORE
import com.revolut.currencies.CurrenciesContract.Orchestrator
import com.revolut.currencies.CurrenciesContract.Presenter
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

class CurrenciesCoreModule {
    val kodein = Kodein.Module(MODULE_CURRENCIES_CORE) {
        bind<Presenter>() with singleton {
            CurrenciesPresenter(instance(), instance(), instance())
        }
        bind<Orchestrator>() with singleton {
            CurrenciesOrchestrator()
        }
    }
}