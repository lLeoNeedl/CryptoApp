package com.demo.cryptoapp.data.api

import com.google.gson.annotations.SerializedName

data class TopCoinInfoDTO(
    @SerializedName("Name")
    val name: String? = null
)