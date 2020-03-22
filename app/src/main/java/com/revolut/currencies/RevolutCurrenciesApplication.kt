package com.revolut.currencies

import android.app.Application
import com.revolut.common.CommonContract
import com.revolut.common.DIComponents

class RevolutCurrenciesApplication : Application(), DIComponents by Components() {

    override fun onCreate() {
        super.onCreate()
    }

}