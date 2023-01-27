package com.demo.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.cryptoapp.data.api.ApiFactory
import com.demo.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfoDbModel(

    @PrimaryKey
    val fromSymbol: String,

    val toSymbol: String,

    val price: Double,

    val lastUpdate: String,

    val highDay: Double,

    val lowDay: Double,

    val lastMarket: String,

    val imageUrl: String
)
