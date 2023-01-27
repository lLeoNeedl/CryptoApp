package com.demo.cryptoapp.data

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.domain.entity.coin_price_info.CoinPriceInfo
import com.demo.cryptoapp.domain.entity.coin_price_info.CoinPriceInfoRawData
import com.demo.cryptoapp.domain.entity.top_coins_info.TopCoinsListOfData
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class CoinInfoRepositoryImpl: CoinInfoRepository {
    
    override fun getTopCoinsInfo(apiKey: String, limit: Int, tSym: String): TopCoinsListOfData {
        TODO("Not yet implemented")
    }

    override fun getPriceListFromInternet(
        apiKey: String,
        fSyms: String,
        tSyms: String
    ): CoinPriceInfoRawData {
        TODO("Not yet implemented")
    }

    override fun getPriceList(): LiveData<List<CoinPriceInfo>> {
        TODO("Not yet implemented")
    }

    override fun getPriceInfoAboutCoin(): LiveData<CoinPriceInfo> {
        TODO("Not yet implemented")
    }

    override fun insertPriceList() {
        TODO("Not yet implemented")
    }
}