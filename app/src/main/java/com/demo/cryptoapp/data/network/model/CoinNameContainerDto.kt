package com.demo.cryptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto(
    @SerializedName("CoinInfo")
    val coinName: CoinNameDto? = null
)