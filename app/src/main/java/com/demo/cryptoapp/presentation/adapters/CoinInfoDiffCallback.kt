package com.demo.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.demo.cryptoapp.domain.entities.CoinInfo

object CoinInfoDiffCallback : DiffUtil.ItemCallback<CoinInfo>() {

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo) =
        oldItem.fromSymbol == newItem.fromSymbol

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo) =
        oldItem == newItem
}