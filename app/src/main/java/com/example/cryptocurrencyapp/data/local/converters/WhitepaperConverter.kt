package com.example.cryptocurrencyapp.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.cryptocurrencyapp.data.remote.dto.Whitepaper
import com.example.cryptocurrencyapp.data.util.JsonParser

@ProvidedTypeConverter
class WhitepaperConverter(private val jsonParser: JsonParser) {
    @TypeConverter
    fun fromJson(json: String?): Whitepaper {
        return json?.let {
            jsonParser.fromJson(it, Whitepaper::class.java) ?: Whitepaper("", "")
        } ?: Whitepaper("", "")
    }

    @TypeConverter
    fun toJson(whitepaper: Whitepaper?): String {
        return whitepaper?.let {
            jsonParser.toJson(it, Whitepaper::class.java) ?: "{}"
        } ?: "{}"
    }
}