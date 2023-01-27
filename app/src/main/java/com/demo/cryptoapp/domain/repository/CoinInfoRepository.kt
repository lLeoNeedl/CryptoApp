package com.demo.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.domain.entity.coin_price_info.CoinPriceInfo
import com.demo.cryptoapp.domain.entity.coin_price_info.CoinPriceInfoRawData
import com.demo.cryptoapp.domain.entity.top_coins_info.TopCoinsListOfData

interface CoinInfoRepository {

    fun getTopCoinsInfo(apiKey: String, limit: Int, tSym: String): TopCoinsListOfData

    fun getPriceListFromInternet(
        apiKey: String,
        fSyms: String,
        tSyms: String
    ): CoinPriceInfoRawData

    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    fun getPriceInfoAboutCoin(): LiveData<CoinPriceInfo>

    fun insertPriceList()
}