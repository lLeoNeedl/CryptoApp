package com.demo.cryptoapp.domain.entity.coin_price_info

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRawData(
    val coinPriceInfoJsonObject: JsonObject
)