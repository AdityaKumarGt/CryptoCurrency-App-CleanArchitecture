package com.example.cryptocurrencyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetails(coinDetails: CoinDetailsEntity)

    @Query("DELETE FROM coindetailsentity WHERE coinId = :coinId")
    suspend fun deleteCoinDetails(coinId: String)

    @Query("SELECT * FROM coindetailsentity WHERE coinId = :coinId")
    suspend fun getCoinDetails(coinId: String): CoinDetailsEntity

}