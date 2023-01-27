package com.demo.cryptoapp.data.api

import com.google.gson.annotations.SerializedName

data class CoinPriceInfoDTO(

    @SerializedName("FROMSYMBOL")
    val fromSymbol: String? = null,

    @SerializedName("TOSYMBOL")
    val toSymbol: String? = null,

    @SerializedName("PRICE")
    val price: Double? = null,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Long? = null,

    @SerializedName("HIGHDAY")
    val highDay: Double? = null,

    @SerializedName("LOWDAY")
    val lowDay: Double? = null,

    @SerializedName("LASTMARKET")
    val lastMarket: String? = null,

    @SerializedName("IMAGEURL")
    val imageUrl: String? = null
)
