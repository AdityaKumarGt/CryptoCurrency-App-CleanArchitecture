package com.example.cryptocurrencyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(
        coinEntities: List<CoinEntity>
    )

    @Query("DELETE FROM coinentity")
    suspend fun clearCoins()

    @Query("""
        SELECT *
        FROM coinentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
        UPPER(:query) == symbol
    """)
    suspend fun searchCoins(query: String): List<CoinEntity>

}


