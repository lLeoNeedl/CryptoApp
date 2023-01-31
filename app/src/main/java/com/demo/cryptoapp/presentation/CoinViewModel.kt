package com.demo.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.demo.cryptoapp.data.repository_impl.CoinInfoRepositoryImpl
import com.demo.cryptoapp.domain.usecases.GetCoinInfoListUseCase
import com.demo.cryptoapp.domain.usecases.GetCoinInfoUseCase
import com.demo.cryptoapp.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    application: Application,
    getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    loadDataUseCase: LoadDataUseCase
) : AndroidViewModel(application) {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailedInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}