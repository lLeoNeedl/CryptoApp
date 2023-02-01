package com.demo.cryptoapp.data.di.modules

import android.app.Application
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

    @ApplicationScope
    @Provides
    fun provideCoinInfoDao(application: Application): CoinInfoDao {
        return AppDatabase.getInstance(application).coinInfoDao()
    }

    @ApplicationScope
    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}