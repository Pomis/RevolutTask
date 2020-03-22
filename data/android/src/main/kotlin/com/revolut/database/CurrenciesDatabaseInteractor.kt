package com.revolut.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.revolut.currencies.CurrenciesContract

class CurrenciesDatabaseInteractor(
    private val db: RevolutDatabase
): CurrenciesContract.DatabaseInteractor {

    override suspend fun getLastSavedRates(): Map<String, Float> {
        return db.currencyDao().getCurrencies().associate { Pair(it.code, it.amount) }
    }

    override suspend fun saveRates(rates: Map<String, Float>) {
        db.currencyDao().updateCurrencies(rates.toList().map { CurrencyEntity(it.first, it.second) })
    }
}