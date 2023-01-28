package com.demo.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.demo.cryptoapp.data.CoinInfoRepositoryImpl
import com.demo.cryptoapp.domain.usecases.*
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinInfoRepositoryImpl(application)

    private val getPriceListUseCase = GetPriceListUseCase(repository)
    private val getPriceInfoAboutCoinUseCase = GetPriceInfoAboutCoinUseCase(repository)
    private val getPriceListFromInternetUseCase = GetPriceListFromInternetUseCase(repository)
    private val getTopCoinInfoUseCase = GetTopCoinsInfoUseCase(repository)
    private val insertPriceListUseCase = InsertPriceListUseCase(repository)

    val priceList = getPriceListUseCase.invoke()

    fun getDetailedInfo(fSym: String) =
        getPriceInfoAboutCoinUseCase.invoke(fSym)

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val topCoins = getTopCoinInfoUseCase.invoke()
            val priceList = getPriceListFromInternetUseCase.invoke(topCoins)
            insertPriceListUseCase.invoke(priceList)
        }
//        flowOf(loadData()).retry()
    }
}