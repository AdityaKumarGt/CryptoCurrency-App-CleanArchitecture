package com.example.cryptocurrencyapp.presentation

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailsScreen: Screen("coin_details_screen")
}
