package com.demo.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.domain.entity.CoinPriceInfo

interface CoinInfoRepository {

    fun getTopCoinsInfo(): String

    fun getPriceListFromInternet(fSyms: String): List<CoinPriceInfo>

    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfo>

    fun insertPriceList(list: List<CoinPriceInfo>)
}