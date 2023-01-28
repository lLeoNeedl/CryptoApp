package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.entities.CoinInfo
import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class InsertPriceListUseCase(private val coinInfoRepository: CoinInfoRepository) {

    suspend operator fun invoke(list: List<CoinInfo>) {
        coinInfoRepository.insertPriceList(list)
    }
}