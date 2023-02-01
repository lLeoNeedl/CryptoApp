package com.demo.cryptoapp.data.di.modules

import com.demo.cryptoapp.data.di.annotations.ApplicationScope
import com.demo.cryptoapp.data.repository_impl.CoinInfoRepositoryImpl
import com.demo.cryptoapp.domain.repository.CoinInfoRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    @ApplicationScope
    fun bindRepository(impl: CoinInfoRepositoryImpl): CoinInfoRepository
}