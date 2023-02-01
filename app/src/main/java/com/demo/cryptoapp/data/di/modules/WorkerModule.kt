package com.demo.cryptoapp.data.di.modules

import androidx.lifecycle.ViewModel
import androidx.work.ListenableWorker
import com.demo.cryptoapp.data.di.annotations.ViewModelKey
import com.demo.cryptoapp.data.di.annotations.WorkerKey
import com.demo.cryptoapp.data.workers.ChildWorkerFactory
import com.demo.cryptoapp.data.workers.RefreshDataWorker
import com.demo.cryptoapp.presentation.viewmodels.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    @Binds
    fun bindRefreshDataWorkerFactory(impl: RefreshDataWorker.Factory): ChildWorkerFactory
}