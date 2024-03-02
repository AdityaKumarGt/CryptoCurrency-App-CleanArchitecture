package com.example.cryptocurrencyapp.data.remote.dto

import com.example.cryptocurrencyapp.data.local.CoinDetailsEntity
import com.google.gson.annotations.SerializedName

data class CoinDetailsDto(
    @SerializedName("description")
    val description: String?,
    @SerializedName("development_status")
    val developmentStatus: String?,
    @SerializedName("first_data_at")
    val firstDataAt: String?,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String?,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("last_data_at")
    val lastDataAt: String?,
    val links: Links?,
    val logo: String?,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>?,
    val message: String?,
    val name: String?,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("org_structure")
    val orgStructure: String?,
    @SerializedName("proof_type")
    val proofType: String?,
    val rank: Int,
    @SerializedName("started_at")
    val startedAt: String?,
    val symbol: String?,
    val tags: List<Tag>?,
    val team: List<TeamMember>?,
    val type: String?,
    val whitepaper: Whitepaper?
)


    fun CoinDetailsDto.toCoinDetailsEntity(): CoinDetailsEntity {
        return CoinDetailsEntity(
            coinId = id,
            name = name,
            description = description,
            symbol = symbol,
            rank = rank,
            isActive = isActive,
            tags = tags,
            team = team,
            logoUrl = logo,
            hashAlgorithm = hashAlgorithm,
            startedAt = startedAt,
            whitePaper = whitepaper,
            links = links
        )
    }


