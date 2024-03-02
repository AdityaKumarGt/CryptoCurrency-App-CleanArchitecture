package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.model.CoinDetails
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(fetchFromRemote: Boolean, query: String): Flow<Resource<List<Coin>>>

    suspend fun getCoinById(coinId:String): Flow<Resource<CoinDetails>>

}