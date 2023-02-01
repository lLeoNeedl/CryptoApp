package com.demo.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.demo.cryptoapp.data.database.CoinInfoDao
import com.demo.cryptoapp.data.mapper.CoinInfoMapper
import com.demo.cryptoapp.data.network.ApiService

class RefreshDataWorkerFactory(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinInfoMapper
): WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return RefreshDataWorker(
            appContext,
            workerParameters,
            coinInfoDao,
            apiService,
            mapper
        )
    }
}