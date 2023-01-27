package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.entity.coin_price_info.CoinPriceInfoRawData
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class GetPriceListFromInternetUseCase(private val coinInfoRepository: CoinInfoRepository) {

    operator fun invoke(
        apiKey: String,
        fSyms: String,
        tSyms: String
    ): CoinPriceInfoRawData = coinInfoRepository.getPriceListFromInternet(apiKey, fSyms, tSyms)
}