package com.revolut.currencies

class CurrenciesOrchestrator: CurrenciesContract.Orchestrator {
    override fun getSelectedCurrency() {

    }

    override fun getCurrencies(): List<CurrencyModel> {
        return listOf(
            CurrencyModel("USD", 123f),
            CurrencyModel("EUR", 213f),
            CurrencyModel("AUD", 122f),
            CurrencyModel("RUB", 103f)
        )
    }
}