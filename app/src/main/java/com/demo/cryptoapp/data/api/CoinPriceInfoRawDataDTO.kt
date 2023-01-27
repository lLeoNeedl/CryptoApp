package com.demo.cryptoapp.data.api

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRawDataDTO(
    @SerializedName("RAW")
    val coinPriceJsonObject: JsonObject? = null
)