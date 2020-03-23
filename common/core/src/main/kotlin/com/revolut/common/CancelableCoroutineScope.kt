package com.revolut.common

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CancelableCoroutineScope() : CommonContract.Scope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun cancel() {
        job.cancel()
    }

    override suspend fun <T> ui(block: suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.Main) {
            block()
        }

    override suspend fun <T> io(block: suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.IO) {
            block()
        }

}