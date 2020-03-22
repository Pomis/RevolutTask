package com.revolut.currencies

import android.os.Bundle
import com.revolut.common.BaseActivity
import com.revolut.currencies.core.R
import org.kodein.di.erased.instance

class CurrenciesActivity : BaseActivity() {

    private val presenter: CurrenciesContract.Presenter by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencies)
        kodein = components.currenciesComponent(this)
        presenter.init()
    }

}
