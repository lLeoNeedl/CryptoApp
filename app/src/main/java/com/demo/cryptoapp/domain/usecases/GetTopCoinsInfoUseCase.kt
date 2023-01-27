package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.entity.top_coins_info.TopCoinsListOfData
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class GetTopCoinsInfoUseCase(private val coinInfoRepository: CoinInfoRepository) {

    operator fun invoke(
        apiKey: String,
        limit: Int,
        tSym: String
    ): TopCoinsListOfData = coinInfoRepository.getTopCoinsInfo(apiKey, limit, tSym)
}
