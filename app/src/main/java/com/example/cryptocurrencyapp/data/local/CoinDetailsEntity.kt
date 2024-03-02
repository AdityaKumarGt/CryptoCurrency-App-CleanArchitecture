package com.example.cryptocurrencyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyapp.data.remote.dto.Links
import com.example.cryptocurrencyapp.data.remote.dto.Tag
import com.example.cryptocurrencyapp.data.remote.dto.TeamMember
import com.example.cryptocurrencyapp.data.remote.dto.Whitepaper
import com.example.cryptocurrencyapp.domain.model.CoinDetails

@Entity
data class CoinDetailsEntity(
    @PrimaryKey val coinId: String,
    val name: String?,
    val description: String?,
    val symbol: String?,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<Tag>?,
    val team: List<TeamMember>?,
    val logoUrl: String?,
    val hashAlgorithm: String?,
    val startedAt: String?,
    val whitePaper: Whitepaper?,
    val links: Links?
){

    fun toCoinDetails(): CoinDetails {
        return CoinDetails(
            coinId = coinId,
            name = name,
            description = description,
            symbol = symbol,
            rank = rank,
            isActive = isActive,
            tags = tags,
            team = team,
            logoUrl = logoUrl,
            hashAlgorithm = hashAlgorithm,
            startedAt = startedAt,
            whitePaper = whitePaper,
            links = links
        )
    }
}