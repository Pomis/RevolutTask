package com.revolut.currencies

data class RatesResponse(
    val baseCurrency: String,
    val rates: Map<String, Float>
)