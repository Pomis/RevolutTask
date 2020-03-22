package com.revolut.currencies

interface CurrenciesContract {
    interface View {
        fun init(currenciesPresenter: Presenter)
        fun updateData()
//        fun moveOnTop(position: Int)
        fun notifyItemMovedOnTop(position: Int)
    }

    interface Presenter {
        val currenciesCount: Int

        fun init()
        fun bindItem(itemView: ItemView, position: Int)
        fun onCurrencyClicked(position: Int)
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
        suspend fun getSelectedCurrency(): CurrencyModel
        suspend fun getLastRates(): RatesResponse
    }

    interface Orchestrator {
        fun getSelectedCurrency()
        suspend fun getCurrencies(): List<CurrencyModel>
    }

    companion object {
        const val MODULE_CURRENCIES_CORE = "currencies_core"
        const val MODULE_CURRENCIES_ANDROID = "currencies_android"
    }
}