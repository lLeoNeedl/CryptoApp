package com.demo.cryptoapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.demo.cryptoapp.data.api.ApiFactory
import com.demo.cryptoapp.data.database.AppDatabase
import com.demo.cryptoapp.domain.entity.CoinPriceInfo
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class CoinInfoRepositoryImpl(application: Application) : CoinInfoRepository {

    private val mapper = CoinInfoMapper()

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    override fun getTopCoinsInfo() =
        mapper.parseTopCoinsListOfData(ApiFactory.apiService.getTopCoinsInfo())

    override fun getPriceListFromInternet(fSyms: String): List<CoinPriceInfo> {
        val listDTO = mapper.getPriceListFromRawData(
            ApiFactory
                .apiService
                .getFullPriceList(fSyms = fSyms)
        )
        return mapper.mapListDTOtoListCoinPriceInfo(listDTO)
    }

    override fun getPriceList(): LiveData<List<CoinPriceInfo>> =
        Transformations.map(coinInfoDao.getPriceList()) {
            mapper.mapListDbModelToListCoinPriceInfo(it)
        }

    override fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfo> =
        Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fSym)) {
            mapper.mapDbModelToCoinPriceInfo(it)
        }

    override fun insertPriceList(list: List<CoinPriceInfo>) {
        val listDbModel = mapper.mapListCoinPriceInfoToListDbModel(list)
        coinInfoDao.insertPriceList(listDbModel)
    }
}