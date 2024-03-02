package com.example.cryptocurrencyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyapp.domain.model.Coin

@Entity
data class CoinEntity(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    @PrimaryKey val dbId: Int? = null
) {
    fun toCoins(): Coin {
        return Coin(
            id = id,
            isActive = isActive,
            name = name,
            rank = rank,
            symbol = symbol
        )
    }
}
