package com.revolut.network

import com.revolut.currencies.CurrenciesContract
import com.revolut.currencies.RatesResponse

class CurrenciesNetworkInteractor(
    private val service: RevolutService
) : CurrenciesContract.NetworkInteractor {
    override suspend fun getRates(base: String): RatesResponse {
        return service.getRates(base)
    }
}