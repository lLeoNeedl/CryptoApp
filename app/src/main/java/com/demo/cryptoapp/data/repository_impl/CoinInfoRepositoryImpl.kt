package com.demo.cryptoapp.data.repository_impl

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.demo.cryptoapp.data.network.ApiFactory
import com.demo.cryptoapp.data.database.AppDatabase
import com.demo.cryptoapp.data.datasource.CoinInfoDataSource
import com.demo.cryptoapp.data.datasource.CoinInfoRemoteDataSource
import com.demo.cryptoapp.data.mapper.CoinInfoMapper
import com.demo.cryptoapp.data.workers.RefreshDataWorker
import com.demo.cryptoapp.domain.entities.CoinInfo
import com.demo.cryptoapp.domain.repository.CoinInfoRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinInfoRepositoryImpl @Inject constructor(
    private val application: Application,
    private val dataSource: CoinInfoDataSource,
    private val remoteDataSource: CoinInfoRemoteDataSource,
    private val mapper: CoinInfoMapper
) : CoinInfoRepository {


    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        Transformations.map(dataSource.getPriceList()) {
            it.map { mapper.mapDbModelToCoinInfo(it) }
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        Transformations.map(dataSource.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToCoinInfo(it)
        }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}