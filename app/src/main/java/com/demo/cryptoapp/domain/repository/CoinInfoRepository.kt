package com.demo.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.demo.cryptoapp.domain.entities.CoinInfo

interface CoinInfoRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>

    suspend fun loadData()
}