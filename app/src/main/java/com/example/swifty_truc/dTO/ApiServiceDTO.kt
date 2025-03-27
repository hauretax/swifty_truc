package com.example.swifty_truc.dTO

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Number,
    @SerializedName("scope") val scope: String,
    @SerializedName("created_at") val createdAt: Int,
    @SerializedName("secret_valid_until") val secretValidUntil: Int
)
