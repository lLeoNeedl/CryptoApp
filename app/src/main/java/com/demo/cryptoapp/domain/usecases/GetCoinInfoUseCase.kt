package com.demo.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.domain.repository.CoinInfoRepository
import com.demo.cryptoapp.domain.entities.CoinInfo
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(private val coinInfoRepository: CoinInfoRepository) {

    operator fun invoke(fSym: String): LiveData<CoinInfo> =
        coinInfoRepository.getCoinInfo(fSym)
}