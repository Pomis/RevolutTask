package com.revolut.common

import com.revolut.common.CommonContract.Companion.MODULE_COMMON_CORE
import org.kodein.di.Kodein

class CommonAndroidModule {
    val kodein = Kodein.Module(MODULE_COMMON_CORE) {

    }
}