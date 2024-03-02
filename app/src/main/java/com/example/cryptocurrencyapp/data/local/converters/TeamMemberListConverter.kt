package com.example.cryptocurrencyapp.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.cryptocurrencyapp.data.remote.dto.TeamMember
import com.example.cryptocurrencyapp.data.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class TeamMemberListConverter(private val jsonParser: JsonParser) {
    @TypeConverter
    fun fromJson(json: String?): List<TeamMember>? {
        return json?.let {
            jsonParser.fromJson<ArrayList<TeamMember>>(
                it,
                object : TypeToken<ArrayList<TeamMember>>() {}.type
            ) ?: emptyList()
        }
    }

    @TypeConverter
    fun toJson(teamMembers: List<TeamMember>?): String {
        return teamMembers?.let {
            jsonParser.toJson(
                it,
                object : TypeToken<ArrayList<TeamMember>>() {}.type
            ) ?: "[]"
        } ?: "[]"
    }
}