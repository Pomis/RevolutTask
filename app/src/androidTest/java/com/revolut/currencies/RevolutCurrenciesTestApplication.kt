package com.revolut.currencies

import android.app.Activity
import android.app.Application
import com.revolut.common.CommonContract
import com.revolut.common.CommonCoreModule
import com.revolut.common.DIComponents
import com.revolut.database.DatabaseAndroidModule
import com.revolut.network.NetworkAndroidModule
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

class RevolutCurrenciesTestApplication : Application(), DIComponents by object : DIComponents {
    override fun currenciesComponent(activity: Activity) = Kodein {
        import(CurrenciesCoreModule().kodein)
        import(CurrenciesAndroidModule(activity).kodein)
        import(CommonCoreModule().kodein)
        import(DatabaseAndroidModule().kodein)

        bind<CurrenciesContract.NetworkInteractor>() with singleton {
            object : CurrenciesContract.NetworkInteractor {
                override suspend fun getRates(base: String): RatesResponse {
                    return RatesResponse("USD", rates = mapOf(
                        "EUR" to 0.9f,
                        "RUB" to 79f,
                        "CAD" to 1.3f
                    ))
                }

            }
        }
    }
}