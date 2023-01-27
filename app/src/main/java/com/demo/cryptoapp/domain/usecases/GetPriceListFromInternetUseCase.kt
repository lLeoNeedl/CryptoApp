package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.entity.CoinPriceInfo
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class GetPriceListFromInternetUseCase(private val coinInfoRepository: CoinInfoRepository) {

    operator fun invoke(fSyms: String): List<CoinPriceInfo> =
        coinInfoRepository.getPriceListFromInternet(fSyms)
}