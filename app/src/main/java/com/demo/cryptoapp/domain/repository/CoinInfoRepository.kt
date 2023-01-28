package com.demo.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.domain.entities.CoinInfo

interface CoinInfoRepository {

    suspend fun getTopCoinsInfo(): String

    suspend fun getPriceListFromInternet(fSyms: String): List<CoinInfo>

    fun getPriceList(): LiveData<List<CoinInfo>>

    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinInfo>

    suspend fun insertPriceList(list: List<CoinInfo>)
}