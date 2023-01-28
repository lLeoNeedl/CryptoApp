package com.demo.cryptoapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.demo.cryptoapp.data.api.ApiFactory
import com.demo.cryptoapp.data.database.AppDatabase
import com.demo.cryptoapp.domain.entities.CoinInfo
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class CoinInfoRepositoryImpl(application: Application) : CoinInfoRepository {

    private val mapper = CoinInfoMapper()

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    override suspend fun getTopCoinsInfo() =
        mapper.parseTopCoinsListOfData(ApiFactory.apiService.getTopCoinsInfo())

    override suspend fun getPriceListFromInternet(fSyms: String): List<CoinInfo> {
        val listDTO = mapper.getPriceListFromRawData(
            ApiFactory
                .apiService
                .getFullPriceList(fSyms = fSyms)
        )
        return mapper.mapListDTOtoListCoinPriceInfo(listDTO)
    }

    override fun getPriceList(): LiveData<List<CoinInfo>> =
        Transformations.map(coinInfoDao.getPriceList()) {
            mapper.mapListDbModelToListCoinPriceInfo(it)
        }

    override fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinInfo> =
        Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fSym)) {
            mapper.mapDbModelToCoinPriceInfo(it)
        }

    override suspend fun insertPriceList(list: List<CoinInfo>) {
        val listDbModel = mapper.mapListCoinPriceInfoToListDbModel(list)
        coinInfoDao.insertPriceList(listDbModel)
    }
}