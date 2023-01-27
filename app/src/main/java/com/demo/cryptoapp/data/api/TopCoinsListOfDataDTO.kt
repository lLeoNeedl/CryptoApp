package com.demo.cryptoapp.data.api

import com.google.gson.annotations.SerializedName

data class TopCoinsListOfDataDTO(
    @SerializedName("Data")
    val data: List<TopCoinDTO>? = null
)