package com.demo.cryptoapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinInfoDbModel::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val lock = Any()
        private const val DB_NAME = "main.db"

        fun getInstance(application: Application): AppDatabase {
            synchronized(lock) {
                INSTANCE?.let { return it }
            }
            synchronized(lock) {
                INSTANCE?.let { return it }
                val db = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration().build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinInfoDao
}