package com.revolut.currencies

import android.app.Activity
import android.view.ViewGroup
import com.revolut.currencies.CurrenciesContract.Companion.MODULE_CURRENCIES_ANDROID
import kotlinx.android.synthetic.main.activity_currencies.*
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

class CurrenciesAndroidModule(activity: Activity) {
    val kodein = Kodein.Module(MODULE_CURRENCIES_ANDROID) {
        bind<ViewGroup>() with singleton { activity.currencies_container }
        bind<CurrenciesContract.View>() with singleton { CurrenciesView(instance()) }
    }
}