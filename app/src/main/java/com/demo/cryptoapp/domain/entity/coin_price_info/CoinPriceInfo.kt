package com.demo.cryptoapp.domain.entity.coin_price_info

import com.demo.cryptoapp.data.api.ApiFactory
import com.demo.cryptoapp.utils.convertTimestampToTime

data class CoinPriceInfo(

    val type: String,

    val market: String,

    val fromSymbol: String,

    val toSymbol: String,

    val flags: String,

    val price: Double,

    val lastUpdate: Long,

    val lastVolume: Double,

    val lastVolumeTo: Double,

    val lastTradeId: String,

    val volumeDay: Double,

    val volumeDayTo: Double,

    val volume24Hour: Double,

    val volume24HourTo: Double,

    val openDay: Double,

    val highDay: Double,

    val lowDay: Double,

    val open24Hour: Double,

    val high24Hour: Double,

    val low24Hour: Double,

    val lastMarket: String,

    val volumeHour: Double,

    val volumeHourTo: Double,

    val openHour: Double,

    val highHour: Double,

    val lowHour: Double,

    val topTierVolume24Hour: Double,

    val topTierVolume24HourTo: Double,

    val change24Hour: Double,

    val changePCT24Hour: Double,

    val changeDay: Double,

    val changePCTDay: Double,

    val supply: Int,

    val mktCap: Double,

    val totalVolume24Hour: Double,

    val totalVolume24HourTo: Double,

    val totalTopTierVolume24Hour: Double,

    val totalTopTierVolume24HourTo: Double,

    val imageUrl: String
) {
    fun getFormattedTime() = convertTimestampToTime(lastUpdate)

    fun getFullImageUrl() = ApiFactory.BASE_IMAGE_URL + imageUrl
}
