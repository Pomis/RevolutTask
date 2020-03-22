package com.revolut.currencies

import java.io.IOException

class CurrenciesOrchestrator(
    private val newtworkInteractor: CurrenciesContract.NetworkInteractor,
    private val databaseInteractor: CurrenciesContract.DatabaseInteractor
) : CurrenciesContract.Orchestrator {

    override suspend fun getLatestRates(): Map<String, Float> {
        return try {
            val rates = mapOf(
                Pair(
                    CurrenciesDefaultConfig.DefaultCurrency.currencyCode,
                    CurrenciesDefaultConfig.DefaultCurrency.amount
                )
            ).plus(
                newtworkInteractor.getRates("USD").rates
            )
            databaseInteractor.saveRates(rates)
            rates
        } catch (e: IOException) {
            databaseInteractor.getLastSavedRates()
        }
    }

}