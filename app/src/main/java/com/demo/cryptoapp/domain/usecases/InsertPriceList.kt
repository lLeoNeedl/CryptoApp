package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.entity.CoinPriceInfo
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class InsertPriceList(private val coinInfoRepository: CoinInfoRepository) {

    operator fun invoke(list: List<CoinPriceInfo>) {
        coinInfoRepository.insertPriceList(list)
    }
}