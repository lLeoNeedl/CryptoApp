package com.demo.cryptoapp.domain.usecases

import com.demo.cryptoapp.domain.repository.CoinInfoRepository

class GetTopCoinsInfoUseCase(private val coinInfoRepository: CoinInfoRepository) {

    suspend operator fun invoke(): String = coinInfoRepository.getTopCoinsInfo()
}
