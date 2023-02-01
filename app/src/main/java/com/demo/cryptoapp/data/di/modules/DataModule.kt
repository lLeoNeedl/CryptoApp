package com.demo.cryptoapp.data.di.modules

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import com.demo.cryptoapp.data.database.AppDatabase
import com.demo.cryptoapp.data.database.CoinInfoDao
import com.demo.cryptoapp.data.di.annotations.ApplicationScope
import com.demo.cryptoapp.data.network.ApiFactory
import com.demo.cryptoapp.data.network.ApiService
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideCoinInfoDao(application: Application): CoinInfoDao {
        return AppDatabase.getInstance(application).coinInfoDao()
    }

    @Provides
    @ApplicationScope
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }

    @Provides
    fun provideNotificationManager(application: Application): NotificationManager {
        return application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}