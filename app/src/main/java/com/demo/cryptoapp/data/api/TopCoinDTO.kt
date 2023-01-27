package com.demo.cryptoapp.data.api

import com.google.gson.annotations.SerializedName

data class TopCoinDTO(
    @SerializedName("CoinInfo")
    val coinInfo: TopCoinInfoDTO? = null
)