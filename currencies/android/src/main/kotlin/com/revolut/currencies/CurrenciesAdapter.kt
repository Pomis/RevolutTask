package com.revolut.currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolut.currencies.core.R

class CurrenciesAdapter() : RecyclerView.Adapter<CurrenciesItemViewHolder>() {

    lateinit var presenter: CurrenciesContract.Presenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesItemViewHolder {
        return CurrenciesItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.currencies_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return presenter.currenciesCount
    }

    override fun onBindViewHolder(holder: CurrenciesItemViewHolder, position: Int) {
        presenter.bindItem(holder, position)
    }
}