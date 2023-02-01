package com.demo.cryptoapp.data.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.demo.cryptoapp.R
import com.demo.cryptoapp.data.database.CoinInfoDao
import com.demo.cryptoapp.data.mapper.CoinInfoMapper
import com.demo.cryptoapp.data.network.ApiFactory
import com.demo.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay
import java.lang.reflect.Constructor
import javax.inject.Inject

class RefreshDataWorker (
    private val context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinInfoMapper,
    private val notificationManager: NotificationManager
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        var isLoading = false
        while (true) {
            try {
                if (!isLoading) {
                    showNotification()
                }
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fromSymbols = mapper.mapNamesListToString(topCoins)
                val jsonContainer =
                    apiService.getFullPriceList(fSyms = fromSymbols)
                val coinInfoDtoList =
                    mapper.mapJsonContainerToListDto(jsonContainer)
                val dbModelList =
                    coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
                isLoading = true
            } catch (e: Exception) {
                notificationManager.cancel(1)
                isLoading = false
            }
            delay(10000)
        }
    }

    private fun showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Background update")
            .setContentText("The content is updating")
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
        notificationManager.notify(1, notification)
    }


    companion object {
        private const val CHANNEL_ID = "Background update_id"
        private const val CHANNEL_NAME = "Background update"

        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
    }

    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService,
        private val mapper: CoinInfoMapper,
        private val notificationManager: NotificationManager
    ): ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                coinInfoDao,
                apiService,
                mapper,
                notificationManager
            )
        }
    }
}
