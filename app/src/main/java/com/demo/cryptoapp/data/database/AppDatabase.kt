package com.demo.cryptoapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.cryptoapp.domain.entity.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"

        fun getInstance(application: Application): AppDatabase {
            db?.let { return it }
            val instance = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().build()
            db = instance
            return instance
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}