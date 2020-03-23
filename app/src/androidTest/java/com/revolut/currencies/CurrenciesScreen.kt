package com.revolut.currencies

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspresso.screens.KScreen
import org.hamcrest.Matcher

object CurrenciesScreen : KScreen<CurrenciesScreen>() {
    override val layoutId: Int? = R.layout.activity_currencies
    override val viewClass: Class<*>? = CurrenciesActivity::class.java

    val currenciesList = KRecyclerView(
        { withId(R.id.rv_currencies) },
        { itemType(::CurrencyItem) }
    )
}

class CurrencyItem(parent: Matcher<View>) : KRecyclerItem<CurrencyItem>(parent) {
    val code = KTextView(parent) { withId(R.id.tv_currency_item_code) }
    val value = KEditText(parent) { withId(R.id.et_currency_item_amount) }
}