package com.revolut.currencies

import android.opengl.Visibility
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_currencies.view.*

class CurrenciesView(
    private val viewGroup: ViewGroup,
    private val adapter: CurrenciesAdapter
) : CurrenciesContract.View {

    lateinit var presenter: CurrenciesContract.Presenter

    override fun init(currenciesPresenter: CurrenciesContract.Presenter) {
        this.presenter = currenciesPresenter
        adapter.presenter = currenciesPresenter
        viewGroup.rv_currencies.adapter = adapter
        viewGroup.rv_currencies.layoutManager = LinearLayoutManager(viewGroup.context)
    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun updateItem(position: Int) {
        adapter.notifyItemChanged(position, 0)
    }

    override fun notifyItemMovedOnTop(position: Int) {
        viewGroup.rv_currencies.layoutManager?.scrollToPosition(0)
        adapter.notifyItemMoved(position, 0)
        adapter.notifyItemMoved(1, position)
    }

    override fun showError() {
        viewGroup.tv_currencies_load_error.visibility = View.VISIBLE
    }

    override fun hideError() {
        viewGroup.tv_currencies_load_error.visibility = View.GONE
    }
}