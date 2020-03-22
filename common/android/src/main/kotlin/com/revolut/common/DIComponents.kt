package com.revolut.common

import android.app.Activity
import org.kodein.di.Kodein

interface DIComponents {
    fun currenciesComponent(activity: Activity): Kodein
}