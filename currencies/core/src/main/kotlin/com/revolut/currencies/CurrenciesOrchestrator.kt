package com.revolut.currencies

import java.lang.Exception

class CurrenciesOrchestrator(
    private val newtworkInteractor: CurrenciesContract.NetworkInteractor
): CurrenciesContract.Orchestrator {
    override fun getSelectedCurrency() {

    }

    override suspend fun getCurrencies(): List<CurrencyModel> {
        return try {
            newtworkInteractor.getRates("USD")
                .rates.map { CurrencyModel(it.key, it.value) }
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }
}