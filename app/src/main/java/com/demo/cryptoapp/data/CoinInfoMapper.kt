package com.demo.cryptoapp.data

import com.demo.cryptoapp.data.api.ApiFactory
import com.demo.cryptoapp.data.api.CoinInfoDTO
import com.demo.cryptoapp.data.api.CoinInfoRawDataDTO
import com.demo.cryptoapp.data.api.TopCoinsListOfDataDTO
import com.demo.cryptoapp.data.database.CoinInfoDbModel
import com.demo.cryptoapp.domain.entities.CoinInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinInfoMapper {

    fun parseTopCoinsListOfData(topCoinsListOfDataDTO: TopCoinsListOfDataDTO) =
        topCoinsListOfDataDTO.data?.map { it.coinInfo?.name }?.joinToString(",") ?: ""

    fun getPriceListFromRawData(rawDataDTO: CoinInfoRawDataDTO): List<CoinInfoDTO> {
        val result = mutableListOf<CoinInfoDTO>()
        val jsonObject = rawDataDTO.coinPriceJsonObject ?: return listOf()
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDTO::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    private fun mapDTOtoCoinPriceInfo(coinInfoDTO: CoinInfoDTO) = CoinInfo(
        fromSymbol = coinInfoDTO.fromSymbol ?: "",
        toSymbol = coinInfoDTO.toSymbol ?: "",
        price = coinInfoDTO.price ?: 0.0,
        lastUpdate = getFormattedTime(coinInfoDTO.lastUpdate),
        highDay = coinInfoDTO.highDay ?: 0.0,
        lowDay = coinInfoDTO.lowDay ?: 0.0,
        lastMarket = coinInfoDTO.lastMarket ?: "",
        imageUrl = getFullImageUrl(coinInfoDTO.imageUrl)
    )

    fun mapListDTOtoListCoinPriceInfo(list: kotlin.collections.List<CoinInfoDTO>) =
        list.map { mapDTOtoCoinPriceInfo(it) }

    private fun mapCoinPriceInfoToDbModel(coinInfo: CoinInfo) =
        CoinInfoDbModel(
            fromSymbol = coinInfo.fromSymbol,
            toSymbol = coinInfo.toSymbol,
            price = coinInfo.price,
            lastUpdate = coinInfo.lastUpdate,
            highDay = coinInfo.highDay,
            lowDay = coinInfo.lowDay,
            lastMarket = coinInfo.lastMarket,
            imageUrl = coinInfo.imageUrl
        )

    fun mapListDbModelToListCoinPriceInfo(list: List<CoinInfoDbModel>) =
        list.map { mapDbModelToCoinPriceInfo(it) }

    fun mapDbModelToCoinPriceInfo(coinInfoDbModel: CoinInfoDbModel) =
        CoinInfo(
            fromSymbol = coinInfoDbModel.fromSymbol,
            toSymbol = coinInfoDbModel.toSymbol,
            price = coinInfoDbModel.price,
            lastUpdate = coinInfoDbModel.lastUpdate,
            highDay = coinInfoDbModel.highDay,
            lowDay = coinInfoDbModel.lowDay,
            lastMarket = coinInfoDbModel.lastMarket,
            imageUrl = coinInfoDbModel.imageUrl
        )

    fun mapListCoinPriceInfoToListDbModel(list: List<CoinInfo>) =
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
