package com.revolut.currencies

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mynameismidori.currencypicker.ExtendedCurrency
import kotlinx.android.synthetic.main.currencies_item.view.*
import java.util.*

class CurrenciesItemViewHolder(
    private val presenter: CurrenciesContract.Presenter,
    itemView: View
): RecyclerView.ViewHolder(itemView), CurrenciesContract.ItemView {

    private val currencyCode = itemView.tv_currency_item_code
    private val currencyName = itemView.tv_currency_item_name
    private val currencyFlag = itemView.iv_currency_item_flag
    private val currencyAmount = itemView.et_currency_item_amount

    init {
        currencyAmount.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("test", "edited text $p0")
            }
        })
    }

    override fun setCurrencyCode(code: String) {
        val currency = Currency.getAvailableCurrencies().find { it.currencyCode == code }
        currencyFlag.setImageDrawable(itemView.context.getDrawable(ExtendedCurrency.getCurrencyByISO(code).flag))
        currencyCode.text = code
        currencyName.text = currency?.displayName
    }

    override fun setCurrencyName(name: String) {
    }

    override fun setCurrencyAmount(amount: Float) {
        currencyAmount.setText(amount.toString(), TextView.BufferType.EDITABLE)
    }

    override fun startEditing() {
        currencyAmount.requestFocus()
        currencyAmount.setSelection(currencyAmount.text.length)
    }
}