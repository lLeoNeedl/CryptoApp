package com.demo.cryptoapp.data.di.modules

import androidx.lifecycle.ViewModel
import com.demo.cryptoapp.data.di.annotations.ViewModelKey
import com.demo.cryptoapp.presentation.viewmodels.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinViewModel(impl: CoinViewModel): ViewModel
}