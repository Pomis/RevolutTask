package com.revolut.currencies

import android.app.Activity
import com.revolut.common.CommonAndroidModule
import com.revolut.network.NetworkAndroidModule
import org.kodein.di.Kodein

class Components {
    fun currenciesComponent(activity: Activity) = Kodein {
        import(CurrenciesCoreModule().kodein)
        import(CurrenciesAndroidModule(activity).kodein)
        import(CommonAndroidModule().kodein)
        import(NetworkAndroidModule().kodein)
    }
}