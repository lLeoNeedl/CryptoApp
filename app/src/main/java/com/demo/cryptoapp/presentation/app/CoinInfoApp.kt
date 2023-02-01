package com.demo.cryptoapp.presentation.app

import android.app.Application
import androidx.work.Configuration
import com.demo.cryptoapp.data.database.AppDatabase
import com.demo.cryptoapp.data.di.components.DaggerApplicationComponent
import com.demo.cryptoapp.data.mapper.CoinInfoMapper
import com.demo.cryptoapp.data.network.ApiFactory
import com.demo.cryptoapp.data.workers.RefreshDataWorkerFactory

class CoinInfoApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinInfoDao(),
                    ApiFactory.apiService,
                    CoinInfoMapper()
                )
            )
            .build()
    }
}