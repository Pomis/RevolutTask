package com.revolut.network

import com.revolut.currencies.CurrenciesContract
import com.revolut.currencies.RatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutService {
    @GET("/android/latest")
    suspend fun getRates(@Query("base") base: String): RatesResponse
}