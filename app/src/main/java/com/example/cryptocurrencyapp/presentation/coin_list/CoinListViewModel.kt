package com.example.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    var state by mutableStateOf(CoinListState())

    private var searchJob: Job? = null

    init {
        getCoins()
    }

    fun onEvent(event: CoinListEvent) {
        when (event) {
            CoinListEvent.Refresh -> {
                getCoins(fetchFromRemote = true)
            }

            is CoinListEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)  //whenever we are typing something,
                searchJob?.cancel()   // it cancels the any type of searching going on in the background,
                searchJob = viewModelScope.launch {// until we stops typing for 0.5 seconds
                    delay(500L)
                    getCoins()
                }
            }
        }
    }


    private fun getCoins(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            getCoinsUseCase(fetchFromRemote, query)
                .collect{ result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(coins = it)
                        }
                    }

                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)

                    }
                }
            }
        }
    }

}