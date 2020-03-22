package com.revolut.currencies

import kotlinx.coroutines.channels.ReceiveChannel

interface CurrenciesContract {
    interface View {
        fun init(currenciesPresenter: Presenter)
        fun updateData()
        //        fun moveOnTop(position: Int)
        fun notifyItemMovedOnTop(position: Int)

        fun updateItem(position: Int)
        fun showError()
    }

    interface Presenter {
        val currenciesCount: Int

        fun init()
        fun bindItem(itemView: ItemView, position: Int)
        fun onCurrencyClicked(position: Int)
        fun onBaseCurrencyAmountEdited(newValue: String?)
    }

    interface ItemView {
        fun setCurrencyCode(code: String)
        fun setCurrencyName(name: String)
        fun setCurrencyAmount(amount: Float)
        fun startEditing()
    }

    interface NetworkInteractor {
        suspend fun getRates(base: String): RatesResponse
    }

    interface DatabaseInteractor {
        suspend fun getLastSavedRates(): Map<String, Float>
        suspend fun saveRates(rates: Map<String, Float>)
    }

    interface Orchestrator {
//        suspend fun getInitialCurrencies(): MutableList<CurrencyModel>
        suspend fun getLatestRates(): Map<String, Float>
    }

    companion object {
        const val MODULE_CURRENCIES_CORE = "currencies_core"
        const val MODULE_CURRENCIES_ANDROID = "currencies_android"
    }
}