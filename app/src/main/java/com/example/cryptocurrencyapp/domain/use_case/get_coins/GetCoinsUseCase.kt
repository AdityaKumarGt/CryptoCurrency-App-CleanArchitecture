package com.example.cryptocurrencyapp.domain.use_case.get_coins

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

     suspend operator fun invoke(fetchFromRemote: Boolean, query: String): Flow<Resource<List<Coin>>> {
        return repository.getCoins(fetchFromRemote,query)
    }

}