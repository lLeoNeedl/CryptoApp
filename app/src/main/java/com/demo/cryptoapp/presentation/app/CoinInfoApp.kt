package com.demo.cryptoapp.presentation.app

import android.app.Application
import androidx.work.Configuration
import com.demo.cryptoapp.data.di.components.DaggerApplicationComponent
import com.demo.cryptoapp.data.workers.CoinInfoWorkerFactory
import javax.inject.Inject

class CoinInfoApp : Application(), Configuration.Provider {

    @Inject
    lateinit var dataWorkerFactory: CoinInfoWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(dataWorkerFactory)
            .build()
    }
}