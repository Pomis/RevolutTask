package com.revolut.currencies

import com.revolut.common.CommonContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class TestCoroutineScope : CommonContract.Scope {
    override suspend fun <T> ui(block: suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.Unconfined) { block() }

    override suspend fun <T> io(block: suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.Unconfined) {
            block()
        }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Unconfined
}