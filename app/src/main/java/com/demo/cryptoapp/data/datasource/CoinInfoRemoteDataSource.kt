package com.demo.cryptoapp.data.datasource

import com.demo.cryptoapp.data.network.ApiService
import com.demo.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.demo.cryptoapp.data.network.model.CoinNamesListDto
import retrofit2.http.Query

interface CoinInfoRemoteDataSource {

    suspend fun getTopCoinsInfo(): CoinNamesListDto

    suspend fun getFullPriceList(fSyms: String): CoinInfoJsonContainerDto
}