package com.demo.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.domain.repository.CoinInfoRepository
import com.demo.cryptoapp.domain.entity.coin_price_info.CoinPriceInfo
import com.demo.cryptoapp.domain.entity.coin_price_info.CoinPriceInfoRawData

class GetPriceInfoAboutCoinUseCase(private val coinInfoRepository: CoinInfoRepository) {

    operator fun invoke(): LiveData<CoinPriceInfo> = coinInfoRepository.getPriceInfoAboutCoin()
}