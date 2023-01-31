package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.repository.CoinInfoRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val coinInfoRepository: CoinInfoRepository) {

    operator fun invoke() {
        coinInfoRepository.loadData()
    }
}