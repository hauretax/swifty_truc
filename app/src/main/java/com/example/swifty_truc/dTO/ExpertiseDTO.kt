package com.example.swifty_truc.dTO

import com.google.gson.annotations.SerializedName

data class ExpertiseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("url") val url: String,
    @SerializedName("kind") val kind: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("expertises_users_url") val expertisesUsersUrl: String
)