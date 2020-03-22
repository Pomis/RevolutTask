package com.revolut.database

import androidx.room.*

@Dao
interface CurrenciesDao {

    @Query("SELECT * FROM currencies")
    fun getCurrencies() : List<CurrencyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateCurrencies(currencies: List<CurrencyEntity>)

}