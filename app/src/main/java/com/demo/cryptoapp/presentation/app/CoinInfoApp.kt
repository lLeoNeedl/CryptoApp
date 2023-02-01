package com.demo.cryptoapp.presentation.app

import android.app.Application
import com.demo.cryptoapp.data.di.components.DaggerApplicationComponent

class CoinInfoApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}