package com.demo.cryptoapp.presentation.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.demo.cryptoapp.R
import com.demo.cryptoapp.databinding.ItemCoinInfoBinding
import com.demo.cryptoapp.domain.entities.CoinInfo
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinInfoAdapter @Inject constructor(private val application: Application) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinClickListener: ((CoinInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding =
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        val binding = holder.binding
        with(binding) {
            with(coin) {
                val symbolsTemplate =
                    application.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate =
                    application.resources.getString(R.string.last_update_template)

                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPriceInfo.text = price.toString()
                tvTimeOfUpdate.text = String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get().load(imageUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClickListener?.invoke(this)
                }
            }
        }
    }
}
