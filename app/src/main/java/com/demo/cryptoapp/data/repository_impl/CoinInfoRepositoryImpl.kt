package com.demo.cryptoapp.data.repository_impl

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.demo.cryptoapp.data.network.ApiFactory
import com.demo.cryptoapp.data.database.AppDatabase
import com.demo.cryptoapp.data.mapper.CoinInfoMapper
import com.demo.cryptoapp.domain.entities.CoinInfo
import com.demo.cryptoapp.domain.repository.CoinInfoRepository
import kotlinx.coroutines.delay

class CoinInfoRepositoryImpl(application: Application) : CoinInfoRepository {

    private val mapper = CoinInfoMapper()
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService


    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        Transformations.map(coinInfoDao.getPriceList()) {
            it.map { mapper.mapDbModelToCoinInfo(it) }
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToCoinInfo(it)
        }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fromSymbols = mapper.mapNamesListToString(topCoins)
                val jsonContainer =
                    apiService.getFullPriceList(fSyms = fromSymbols)
                val coinInfoDtoList =
                    mapper.mapJsonContainerToListDto(jsonContainer)
                val dbModelList =
                    coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}