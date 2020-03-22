package com.revolut.common

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance

abstract class BaseActivity : AppCompatActivity(), KodeinAware {

    override lateinit var kodein: Kodein

    protected val components: DIComponents by lazy { application as DIComponents }

    private val coroutineScope: CancelableCoroutineScope by instance()

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

}