package com.demo.cryptoapp.data.datasource

import com.demo.cryptoapp.data.network.ApiService
import com.demo.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.demo.cryptoapp.data.network.model.CoinNamesListDto
import javax.inject.Inject

class CoinInfoRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : CoinInfoRemoteDataSource {

    override suspend fun getTopCoinsInfo() = apiService.getTopCoinsInfo(limit = 50)

    override suspend fun getFullPriceList(fSyms: String) =
        apiService.getFullPriceList(fSyms = fSyms)
}