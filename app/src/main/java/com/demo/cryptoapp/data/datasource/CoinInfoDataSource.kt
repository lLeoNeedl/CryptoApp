package com.demo.cryptoapp.data.datasource

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.data.database.CoinInfoDbModel

interface CoinInfoDataSource {

    fun getPriceList(): LiveData<List<CoinInfoDbModel>>

    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinInfoDbModel>

    suspend fun insertPriceList(priceList: List<CoinInfoDbModel>)

}