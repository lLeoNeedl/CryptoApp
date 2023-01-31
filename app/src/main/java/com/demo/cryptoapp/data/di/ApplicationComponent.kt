package com.demo.cryptoapp.data.di

import com.demo.cryptoapp.presentation.CoinPriceListActivity
import dagger.Component

@Component
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
}