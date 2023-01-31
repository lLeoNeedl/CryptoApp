package com.demo.cryptoapp.data.datasource

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.data.database.AppDatabase
import com.demo.cryptoapp.data.database.CoinInfoDbModel
import javax.inject.Inject

class CoinInfoDataSourceImpl @Inject constructor(
    private val database: AppDatabase
) : CoinInfoDataSource {

    override fun getPriceList() =
        database.coinPriceInfoDao().getPriceList()

    override fun getPriceInfoAboutCoin(fSym: String) =
        database.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)

    override suspend fun insertPriceList(priceList: List<CoinInfoDbModel>) =
        database.coinPriceInfoDao().insertPriceList(priceList)
}