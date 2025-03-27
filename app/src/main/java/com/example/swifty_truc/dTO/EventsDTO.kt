package com.example.swifty_truc.dTO

import com.google.gson.annotations.SerializedName


typealias EventsDTO = List<EventDTO>

data class EventDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("location") val location: String?,
    @SerializedName("kind") val kind: String?,
    @SerializedName("max_people") val maxPeople: Int?,
    @SerializedName("nbr_subscribers") val nbrSubscribers: Int,
    @SerializedName("begin_at") val beginAt: String,  // ISO 8601 datetime string
    @SerializedName("end_at") val endAt: String,      // ISO 8601 datetime string
    @SerializedName("campus_ids") val campusIds: List<Int>,
    @SerializedName("cursus_ids") val cursusIds: List<Int>,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("prohibition_of_cancellation") val prohibitionOfCancellation: Int,
    @SerializedName("waitlist") val waitlist: Any?,
    @SerializedName("themes") val themes: List<String>
)
