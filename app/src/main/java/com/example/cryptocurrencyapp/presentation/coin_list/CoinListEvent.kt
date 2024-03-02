package com.example.cryptocurrencyapp.presentation.coin_list

sealed class CoinListEvent {
    object Refresh: CoinListEvent()
    data class OnSearchQueryChange(val query: String): CoinListEvent()

}


