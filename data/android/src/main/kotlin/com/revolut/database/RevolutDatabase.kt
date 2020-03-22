package com.revolut.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class RevolutDatabase: RoomDatabase() {
    abstract fun currencyDao(): CurrenciesDao
}