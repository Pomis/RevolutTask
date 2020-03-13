package com.revolut.network

import com.revolut.currencies.CurrenciesContract
import com.revolut.currencies.RatesResponse

class CurrenciesRepository(
    private val service: RevolutService
) : CurrenciesContract.Repository {
    override suspend fun getRates(base: String): RatesResponse {
        return service.getRates(base)
    }
}