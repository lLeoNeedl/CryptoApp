package com.demo.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CoinPriceInfoDao {

    @androidx.room.Query("SELECT * FROM full_price_list ORDER BY lastUpdate DESC")
    fun getPriceList(): LiveData<List<CoinPriceInfoDbModel>>

    @androidx.room.Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList (priceList: List<CoinPriceInfoDbModel>)
}