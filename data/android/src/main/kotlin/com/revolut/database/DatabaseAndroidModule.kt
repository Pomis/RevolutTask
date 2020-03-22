package com.revolut.database

import androidx.room.Room
import com.revolut.currencies.CurrenciesContract
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

class DatabaseAndroidModule {
    val kodein = Kodein.Module("database_android") {
        bind<RevolutDatabase>() with singleton {
            Room.databaseBuilder(
                instance(),
                RevolutDatabase::class.java, "revolut-db"
            ).build()
        }

        bind<CurrenciesContract.DatabaseInteractor>() with singleton {
            CurrenciesDatabaseInteractor(instance())
        }
    }
}