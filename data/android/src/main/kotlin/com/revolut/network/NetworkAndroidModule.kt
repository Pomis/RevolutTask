package com.revolut.network

import com.google.gson.GsonBuilder
import com.revolut.currencies.CurrenciesContract
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkAndroidModule {
    val kodein = Kodein.Module("network_android") {
        bind() from singleton {
            Retrofit.Builder()
                .baseUrl("https://hiring.revolut.codes/api/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(RevolutService::class.java)
        }
    }
}