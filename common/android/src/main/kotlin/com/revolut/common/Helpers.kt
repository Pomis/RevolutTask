package com.revolut.common

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.setOnUserChangedTextListener(listener: (String) -> Unit) {
    addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (hasFocus()) {
                listener(p0.toString())
            }
        }
    })
}

fun Context.showKeyboard() {
    val imm =  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.SHOW_IMPLICIT)
}

fun Activity.hideKeyboard() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}