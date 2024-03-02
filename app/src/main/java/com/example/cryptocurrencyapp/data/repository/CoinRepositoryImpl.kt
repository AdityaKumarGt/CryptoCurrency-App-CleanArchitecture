package com.example.cryptocurrencyapp.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.local.CoinDatabase
import com.example.cryptocurrencyapp.data.remote.CoinPaprikaAPI
import com.example.cryptocurrencyapp.data.remote.dto.toCoinDetailsEntity
import com.example.cryptocurrencyapp.data.remote.dto.toCoinEntity
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.model.CoinDetails
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaAPI,
    private val db: CoinDatabase
) : CoinRepository {
    private val coinDao = db.coinDao
    private val coinDetailsDao = db.coinDetailsDao

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getCoins(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Coin>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCoins = coinDao.searchCoins(query)
            emit(Resource.Success(
                data = localCoins.map { it.toCoins() }
            ))
            val isDbEmpty = localCoins.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            try {
                val response = api.getCoins()
                response?.let { coins ->
                    coinDao.clearCoins()
                    coinDao.insertCoins(
                        coins.map { it.toCoinEntity() }
                    )
                    val newCoins = coinDao.searchCoins("").map { it.toCoins() }
                    emit(Resource.Success(newCoins))
                    emit(Resource.Loading(false))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

        }

    }


    override suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetails>> {
        return flow {

            try {

                emit(Resource.Loading(true))
                // Attempt to get data from the local database
                val localCoinDetails = coinDetailsDao.getCoinDetails(coinId)

                if (localCoinDetails != null) {
                    emit(Resource.Success(data = localCoinDetails.toCoinDetails()))
                } else {
                    // If local data is not available, fetch from the API
                    val response = api.getCoinById(coinId)
                    response?.let { coinsDetail ->
                        coinDetailsDao.deleteCoinDetails(coinId)
                        coinDetailsDao.insertCoinDetails(response.toCoinDetailsEntity())
                        val newCoins = coinDetailsDao.getCoinDetails(coinId).toCoinDetails()
                        emit(Resource.Success(newCoins))
                    }
                }
            } catch (e: retrofit2.HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }

        }
    }


}