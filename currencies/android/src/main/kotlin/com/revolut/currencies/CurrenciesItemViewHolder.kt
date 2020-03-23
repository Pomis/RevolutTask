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
import com.revolut.common.setOnUserChangedTextListener
import com.revolut.common.showKeyboard
import kotlinx.android.synthetic.main.currencies_item.view.*
import java.text.NumberFormat
import java.util.*


class CurrenciesItemViewHolder(
    private val presenter: CurrenciesContract.Presenter,
    itemView: View
) : RecyclerView.ViewHolder(itemView), CurrenciesContract.ItemView {

    private val currencyCode = itemView.tv_currency_item_code
    private val currencyName = itemView.tv_currency_item_name
    private val currencyFlag = itemView.iv_currency_item_flag
    private val currencyAmount = itemView.et_currency_item_amount
    private val clickIntercepter = itemView.v_currency_item_amount_click_intercepter

    init {
        currencyAmount.setOnUserChangedTextListener {
            if (adapterPosition == 0) {
                presenter.onBaseCurrencyAmountEdited(it)
            }
        }
        clickIntercepter.setOnClickListener {
            presenter.onCurrencyClicked(this, adapterPosition)
        }
    }

    override fun setCurrencyCode(code: String) {
        currencyFlag.setImageDrawable(
            itemView.context.getDrawable(
                ExtendedCurrency.getCurrencyByISO(
                    code
                ).flag
            )
        )
        currencyCode.text = code
    }

    override fun setCurrencyName(name: String) {
        currencyName.text = name
    }

    override fun setCurrencyAmount(amount: Float) {
        currencyAmount.setText(String.format("%1\$.2f", amount), TextView.BufferType.EDITABLE)
    }

    override fun startEditing() {
        currencyAmount.requestFocus()
        currencyAmount.setSelection(currencyAmount.text.length)
        currencyAmount.showKeyboard()
    }
}