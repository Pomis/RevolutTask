package com.revolut.currencies

interface CurrenciesContract {
    interface View {
        fun init(currenciesPresenter: Presenter)
    }

    interface Presenter {
        fun init()
    }

    interface Repository {
        suspend fun getRates(base: String): RatesResponse
    }

    interface Orchestrator {
        fun getCurrencies()
    }

    companion object {
        const val MODULE_CURRENCIES_CORE = "currencies_core"
        const val MODULE_CURRENCIES_ANDROID = "currencies_android"
    }
}