package com.revolut.common

import kotlinx.coroutines.CoroutineScope


interface CommonContract {
    interface Scope : CoroutineScope {
        suspend fun <T> ui(block: suspend CoroutineScope.() -> T): T
        suspend fun <T> io(block: suspend CoroutineScope.() -> T): T
    }

    companion object {
        const val MODULE_COMMON_CORE = "common_core"
    }
}