package com.revolut.common

import java.text.NumberFormat
import java.text.ParseException
import java.util.*

fun String?.toFloatSafe(): Float {
    val format = NumberFormat.getInstance(Locale.getDefault())
    return if (this != null) {
        val number = try {
            format.parse(toString())
        } catch (e: ParseException) {
            0
        }
        number?.toFloat() ?: 0f
    } else {
        0f
    }
}