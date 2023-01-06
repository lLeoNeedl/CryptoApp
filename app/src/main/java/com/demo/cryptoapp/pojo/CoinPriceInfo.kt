package com.demo.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.cryptoapp.api.ApiFactory
import com.demo.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
    @SerializedName("TYPE")
    val type: String? = null,

    @SerializedName("MARKET")
    val market: String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    val toSymbol: String? = null,

    @SerializedName("FLAGS")
    val flags: String? = null,

    @SerializedName("PRICE")
    val price: Double? = null,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Long? = null,

    @SerializedName("LASTVOLUME")
    val lastVolume: Double? = null,

    @SerializedName("LASTVOLUMETO")
    val lastVolumeTo: Double? = null,

    @SerializedName("LASTTRADEID")
    val lastTradeId: String? = null,

    @SerializedName("VOLUMEDAY")
    val volumeDay: Double? = null,

    @SerializedName("VOLUMEDAYTO")
    val volumeDayTo: Double? = null,

    @SerializedName("VOLUME24HOUR")
    val volume24Hour: Double? = null,

    @SerializedName("VOLUME24HOURTO")
    val volume24HourTo: Double? = null,

    @SerializedName("OPENDAY")
    val openDay: Double? = null,

    @SerializedName("HIGHDAY")
    val highDay: Double? = null,

    @SerializedName("LOWDAY")
    val lowDay: Double? = null,

    @SerializedName("OPEN24HOUR")
    val open24Hour: Double? = null,

    @SerializedName("HIGH24HOUR")
    val high24Hour: Double? = null,

    @SerializedName("LOW24HOUR")
    val low24Hour: Double? = null,

    @SerializedName("LASTMARKET")
    val lastMarket: String? = null,

    @SerializedName("VOLUMEHOUR")
    val volumeHour: Double? = null,

    @SerializedName("VOLUMEHOURTO")
    val volumeHourTo: Double? = null,

    @SerializedName("OPENHOUR")
    val openHour: Double? = null,

    @SerializedName("HIGHHOUR")
    val highHour: Double? = null,

    @SerializedName("LOWHOUR")
    val lowHour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOUR")
    val topTierVolume24Hour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    val topTierVolume24HourTo: Double? = null,

    @SerializedName("CHANGE24HOUR")
    val change24Hour: Double? = null,

    @SerializedName("CHANGEPCT24HOUR")
    val changePCT24Hour: Double? = null,

    @SerializedName("CHANGEDAY")
    val changeDay: Double? = null,

    @SerializedName("CHANGEPCTDAY")
    val changePCTDay: Double? = null,

    @SerializedName("SUPPLY")
    val supply: Int? = null,

    @SerializedName("MKTCAP")
    val mktCap: Double? = null,

    @SerializedName("TOTALVOLUME24H")
    val totalVolume24Hour: Double? = null,

    @SerializedName("TOTALVOLUME24HTO")
    val totalVolume24HourTo: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    val totalTopTierVolume24Hour: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    val totalTopTierVolume24HourTo: Double? = null,

    @SerializedName("IMAGEURL")
    val imageUrl: String? = null
) {
    fun getFormattedTime() = convertTimestampToTime(lastUpdate)

    fun getFullImageUrl() = ApiFactory.BASE_IMAGE_URL + imageUrl
}
