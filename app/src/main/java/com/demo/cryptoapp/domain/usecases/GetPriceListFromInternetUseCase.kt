package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.entities.CoinInfo
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class GetPriceListFromInternetUseCase(private val coinInfoRepository: CoinInfoRepository) {

    suspend operator fun invoke(fSyms: String): List<CoinInfo> =
        coinInfoRepository.getPriceListFromInternet(fSyms)
}