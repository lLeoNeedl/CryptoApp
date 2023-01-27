package com.demo.cryptoapp.domain.entity

import com.demo.cryptoapp.data.api.ApiFactory
import com.demo.cryptoapp.utils.convertTimestampToTime

data class CoinPriceInfo(

    val fromSymbol: String,

    val toSymbol: String,

    val price: Double,

    val lastUpdate: String,

    val highDay: Double,

    val lowDay: Double,

    val lastMarket: String,

    val imageUrl: String
)