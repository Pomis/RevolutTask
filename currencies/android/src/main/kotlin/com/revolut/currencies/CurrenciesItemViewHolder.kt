package com.revolut.currencies

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.mynameismidori.currencypicker.ExtendedCurrency
import kotlinx.android.synthetic.main.currencies_item.view.*
import java.text.NumberFormat
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

                if (adapterPosition == 0 && currencyAmount.hasFocus()) {
                    presenter.onBaseCurrencyAmountEdited(p0.toString())
                }
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
        currencyAmount.setText(String.format("%1\$.2f", amount), TextView.BufferType.EDITABLE)
    }

    override fun startEditing() {
        currencyAmount.requestFocus()
        currencyAmount.setSelection(currencyAmount.text.length)
        val imm = itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.SHOW_FORCED)
    }
}