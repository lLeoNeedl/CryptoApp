package com.demo.cryptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNamesListDto(
    @SerializedName("Data")
    val data: List<CoinNameContainerDto>? = null
)