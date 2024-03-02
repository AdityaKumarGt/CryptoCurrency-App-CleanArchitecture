package com.example.cryptocurrencyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cryptocurrencyapp.data.local.converters.LinksConverter
import com.example.cryptocurrencyapp.data.local.converters.TagListConverter
import com.example.cryptocurrencyapp.data.local.converters.TeamMemberListConverter
import com.example.cryptocurrencyapp.data.local.converters.WhitepaperConverter


@Database(
    entities = [CoinEntity::class, CoinDetailsEntity::class],
    version = 1
)
@TypeConverters(
    TagListConverter::class,
    TeamMemberListConverter::class,
    LinksConverter::class,
    WhitepaperConverter::class
)
abstract class CoinDatabase: RoomDatabase()  {
    abstract val coinDao: CoinDao
    abstract val coinDetailsDao: CoinDetailsDao
}