package com.demo.cryptoapp.data

import com.demo.cryptoapp.data.api.ApiFactory
import com.demo.cryptoapp.data.api.CoinPriceInfoDTO
import com.demo.cryptoapp.data.api.CoinPriceInfoRawDataDTO
import com.demo.cryptoapp.data.api.TopCoinsListOfDataDTO
import com.demo.cryptoapp.data.database.CoinPriceInfoDbModel
import com.demo.cryptoapp.domain.entity.CoinPriceInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinInfoMapper {

    fun parseTopCoinsListOfData(topCoinsListOfDataDTO: TopCoinsListOfDataDTO) =
        topCoinsListOfDataDTO.data?.map { it.coinInfo?.name }?.joinToString { "," } ?: ""

    fun getPriceListFromRawData(rawDataDTO: CoinPriceInfoRawDataDTO): List<CoinPriceInfoDTO> {
        val result = mutableListOf<CoinPriceInfoDTO>()
        val jsonObject = rawDataDTO.coinPriceJsonObject ?: return listOf()
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfoDTO::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    private fun mapDTOtoCoinPriceInfo(coinPriceInfoDTO: CoinPriceInfoDTO) = CoinPriceInfo(
        fromSymbol = coinPriceInfoDTO.fromSymbol ?: "",
        toSymbol = coinPriceInfoDTO.toSymbol ?: "",
        price = coinPriceInfoDTO.price ?: 0.0,
        lastUpdate = getFormattedTime(coinPriceInfoDTO.lastUpdate),
        highDay = coinPriceInfoDTO.highDay ?: 0.0,
        lowDay = coinPriceInfoDTO.lowDay ?: 0.0,
        lastMarket = coinPriceInfoDTO.lastMarket ?: "",
        imageUrl = getFullImageUrl(coinPriceInfoDTO.imageUrl)
    )

    fun mapListDTOtoListCoinPriceInfo(list: kotlin.collections.List<CoinPriceInfoDTO>) =
        list.map { mapDTOtoCoinPriceInfo(it) }

    private fun mapCoinPriceInfoToDbModel(coinPriceInfo: CoinPriceInfo) =
        CoinPriceInfoDbModel(
            fromSymbol = coinPriceInfo.fromSymbol,
            toSymbol = coinPriceInfo.toSymbol,
            price = coinPriceInfo.price,
            lastUpdate = coinPriceInfo.lastUpdate,
            highDay = coinPriceInfo.highDay,
            lowDay = coinPriceInfo.lowDay,
            lastMarket = coinPriceInfo.lastMarket,
            imageUrl = coinPriceInfo.imageUrl
        )

    fun mapListDbModelToListCoinPriceInfo(list: List<CoinPriceInfoDbModel>) =
        list.map { mapDbModelToCoinPriceInfo(it) }

    fun mapDbModelToCoinPriceInfo(coinPriceInfoDbModel: CoinPriceInfoDbModel) =
        CoinPriceInfo(
            fromSymbol = coinPriceInfoDbModel.fromSymbol,
            toSymbol = coinPriceInfoDbModel.toSymbol,
            price = coinPriceInfoDbModel.price,
            lastUpdate = coinPriceInfoDbModel.lastUpdate,
            highDay = coinPriceInfoDbModel.highDay,
            lowDay = coinPriceInfoDbModel.lowDay,
            lastMarket = coinPriceInfoDbModel.lastMarket,
            imageUrl = coinPriceInfoDbModel.imageUrl
        )

    fun mapListCoinPriceInfoToListDbModel(list: List<CoinPriceInfo>) =
        list.map { mapCoinPriceInfoToDbModel(it) }

    private fun getFormattedTime(timestamp: Long?): String {
        if (timestamp == null) {
            return ""
        }
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    private fun getFullImageUrl(imageUrl: String?) =
        ApiFactory.BASE_IMAGE_URL + imageUrl
}
