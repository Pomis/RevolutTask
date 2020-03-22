package com.revolut.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface CurrenciesDao {
    @Query("SELECT * FROM currencies")
    fun getCurrencies() : List<CurrencyEntity>

    @Update
    fun updateCurrencies(currencies: List<CurrencyEntity>)
}