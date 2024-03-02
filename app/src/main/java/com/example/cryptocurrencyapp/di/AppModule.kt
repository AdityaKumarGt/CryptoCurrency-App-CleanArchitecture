package com.example.cryptocurrencyapp.di

import android.app.Application
import androidx.room.Room
import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.data.local.CoinDatabase
import com.example.cryptocurrencyapp.data.local.converters.LinksConverter
import com.example.cryptocurrencyapp.data.local.converters.TagListConverter
import com.example.cryptocurrencyapp.data.local.converters.TeamMemberListConverter
import com.example.cryptocurrencyapp.data.local.converters.WhitepaperConverter
import com.example.cryptocurrencyapp.data.remote.CoinPaprikaAPI
import com.example.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyapp.data.util.GsonParser
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaAPI(): CoinPaprikaAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaAPI::class.java)

    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaAPI, db: CoinDatabase): CoinRepository{
        return CoinRepositoryImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideCoinDatabase(app: Application): CoinDatabase {
        return Room.databaseBuilder(
            app,
            CoinDatabase::class.java,
            "coindb.db"
        )
            .addTypeConverter(TagListConverter((GsonParser(Gson()))))
            .addTypeConverter(TeamMemberListConverter((GsonParser(Gson()))))
            .addTypeConverter(LinksConverter((GsonParser(Gson()))))
            .addTypeConverter(WhitepaperConverter((GsonParser(Gson()))))
            .build()
    }

}