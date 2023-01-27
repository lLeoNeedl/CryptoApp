package com.demo.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.domain.repository.CoinInfoRepository
import com.demo.cryptoapp.domain.entity.CoinPriceInfo

class GetPriceInfoAboutCoinUseCase(private val coinInfoRepository: CoinInfoRepository) {

    operator fun invoke(fSym: String): LiveData<CoinPriceInfo> =
        coinInfoRepository.getPriceInfoAboutCoin(fSym)
}