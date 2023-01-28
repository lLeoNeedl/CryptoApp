package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class LoadDataUseCase(private val coinInfoRepository: CoinInfoRepository) {

    suspend operator fun invoke() {
        coinInfoRepository.loadData()
    }
}