package com.example.profitnetworking.networking

import retrofit2.http.Url
import java.net.URI

data class Hint(
    val id: String,
    val tags: String,
    val previewURL: String,
    val largeImageURL: String,
    val likes: Int
)


data class FactResponse(
    val total: Long,
    val totalHits: Int,
    val hits: List<Hint>

)

