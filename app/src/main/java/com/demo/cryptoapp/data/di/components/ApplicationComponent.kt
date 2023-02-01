package com.demo.cryptoapp.data.di.components

import android.app.Application
import com.demo.cryptoapp.data.di.annotations.ApplicationScope
import com.demo.cryptoapp.data.di.modules.*
import com.demo.cryptoapp.presentation.app.CoinInfoApp
import com.demo.cryptoapp.presentation.ui.CoinDetailFragment
import com.demo.cryptoapp.presentation.ui.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [DataModule::class, DomainModule::class, ViewModelModule::class, WorkerModule::class]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(app: CoinInfoApp)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}