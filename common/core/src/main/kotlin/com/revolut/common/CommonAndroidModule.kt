package com.revolut.common

import com.revolut.common.CommonContract.Companion.MODULE_COMMON_CORE
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

class CommonAndroidModule {

    val kodein = Kodein.Module(MODULE_COMMON_CORE) {
        bind() from singleton { CancelableCoroutineScope() }
    }

}