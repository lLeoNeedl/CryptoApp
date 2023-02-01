package com.demo.cryptoapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.demo.cryptoapp.domain.usecases.GetCoinInfoListUseCase
import com.demo.cryptoapp.domain.usecases.GetCoinInfoUseCase
import com.demo.cryptoapp.domain.usecases.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailedInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}