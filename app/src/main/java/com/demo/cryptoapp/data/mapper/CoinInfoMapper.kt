package com.demo.cryptoapp.data.mapper

import com.demo.cryptoapp.data.database.CoinInfoDbModel
import com.demo.cryptoapp.data.network.ApiFactory
import com.demo.cryptoapp.data.network.model.CoinInfoDto
import com.demo.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.demo.cryptoapp.data.network.model.CoinNamesListDto
import com.demo.cryptoapp.domain.entities.CoinInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinInfoMapper @Inject constructor() {

    fun mapDtoToDbModel(coinInfoDTO: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = coinInfoDTO.fromSymbol ?: EMPTY_SYMBOL,
        toSymbol = coinInfoDTO.toSymbol ?: EMPTY_SYMBOL,
        price = coinInfoDTO.price ?: EMPTY_VALUE,
        lastUpdate = coinInfoDTO.lastUpdate ?: EMPTY_VALUE.toLong(),
        highDay = coinInfoDTO.highDay ?: EMPTY_VALUE,
        lowDay = coinInfoDTO.lowDay ?: EMPTY_VALUE,
        lastMarket = coinInfoDTO.lastMarket ?: EMPTY_SYMBOL,
        imageUrl = getFullImageUrl(coinInfoDTO.imageUrl)
    )

    fun mapJsonContainerToListDto(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesList: CoinNamesListDto) =
        namesList.data?.map { it.coinName?.name }?.joinToString(",") ?: ""

    fun mapDbModelToCoinInfo(coinInfoDbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = coinInfoDbModel.fromSymbol,
        toSymbol = coinInfoDbModel.toSymbol,
        price = coinInfoDbModel.price,
        lastUpdate = getFormattedTime(coinInfoDbModel.lastUpdate),
        highDay = coinInfoDbModel.highDay,
        lowDay = coinInfoDbModel.lowDay,
        lastMarket = coinInfoDbModel.lastMarket,
        imageUrl = coinInfoDbModel.imageUrl
    )

    private fun getFormattedTime(timestamp: Long): String {
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    private fun getFullImageUrl(imageUrl: String?) =
        BASE_IMAGE_URL + imageUrl

    companion object {
        private const val EMPTY_SYMBOL = ""
        private const val EMPTY_VALUE = 0.0
        private const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}
