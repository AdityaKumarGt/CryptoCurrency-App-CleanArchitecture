package com.example.cryptocurrencyapp.domain.model

import com.example.cryptocurrencyapp.data.remote.dto.Links
import com.example.cryptocurrencyapp.data.remote.dto.Tag
import com.example.cryptocurrencyapp.data.remote.dto.TeamMember
import com.example.cryptocurrencyapp.data.remote.dto.Whitepaper

data class CoinDetails(
    val coinId: String?,
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

)
