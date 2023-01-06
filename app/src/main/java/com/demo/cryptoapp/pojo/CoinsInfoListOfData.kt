package com.demo.cryptoapp.pojo

import com.google.gson.annotations.SerializedName

data class CoinsInfoListOfData(
    @SerializedName("Data")
    val data: List<Datum>? = null
)