package com.revolut.currencies

import java.io.IOException

class CurrenciesOrchestrator(
    private val newtworkInteractor: CurrenciesContract.NetworkInteractor
) : CurrenciesContract.Orchestrator {

    override suspend fun getLatestRates(): Map<String, Float> {
        return try {
            mapOf(
                Pair(
                    CurrenciesDefaultConfig.DefaultCurrency.currencyCode,
                    CurrenciesDefaultConfig.DefaultCurrency.amount
                )
            ).plus(
                newtworkInteractor.getRates("USD").rates
            )
        } catch (e: IOException) {
            e.printStackTrace()
            mapOf()//NO
        }
    }

}