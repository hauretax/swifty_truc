package com.example.swifty_truc.services

import UserDTO
import android.content.Context

import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import android.util.Base64
import android.util.Log
import com.example.swifty_truc.dTO.AuthResponse
import com.example.swifty_truc.dTO.EventDTO
import com.example.swifty_truc.dTO.ExpertiseDTO
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit
import com.example.swifty_truc.BuildConfig


class ApiService(private val client: OkHttpClient) {

    private var token: String = ""
    private var tokenExpirationTime: Long = 0
    private var isRefreshing: Boolean = false

    suspend fun fetchAuthToken(): AuthResponse {

        val clientId = BuildConfig.CLIENT_ID
        val clientSecret = BuildConfig.CLIENT_SECRET

        val credentials = "$clientId:$clientSecret"
        val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        val requestBody = FormBody.Builder()
            .add("grant_type", "client_credentials")
            .build()

        val request = Request.Builder()
            .url("https://api.intra.42.fr/oauth/token")
            .post(requestBody)
            .addHeader("Authorization", "Basic $encodedCredentials")
            .build()
        val authResponse: AuthResponse = makeRequest(request)
        token = authResponse.accessToken

        val expiresIn = authResponse.expiresIn.toLong() * 1000L
//        val expiresIn = 60 * 1000L
        tokenExpirationTime = System.currentTimeMillis() + expiresIn
        return authResponse
    }

    private fun isTokenExpired(): Boolean {
        Log.d("correction", "token is expired ? ")
        return System.currentTimeMillis() >= tokenExpirationTime
    }

    suspend fun fetchUserData(userName:String): UserDTO {
        val request = Request.Builder()
            .url("https://api.intra.42.fr/v2/users/$userName")
            .header("Authorization", "Bearer $token")
            .build()

        return makeRequest(request)
    }

     /*
     * update local expertise_list after fetch it on api
     * */
    suspend fun fetchExpertiseList(context: Context) {
        val request = Request.Builder()
            .url("https://api.intra.42.fr/v2/expertises?per_page=100")
            .header("Authorization", "Bearer $token")
            .build()

        val expertiseList: List<ExpertiseDTO> = makeRequest(request)

        val sharedPreferences = context.getSharedPreferences("expertise_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit() {
            putString("expertise_list", Gson().toJson(expertiseList))
        }
    }

    suspend fun fetchEventsUserData(id: Int): List<EventDTO> {
        val request = Request.Builder()
            .url("https://api.intra.42.fr/v2/users/$id/events")
            .header("Authorization", "Bearer $token")
            .build()

        return makeRequest<List<EventDTO>>(request)
    }

    suspend fun refreshAuthToken(refreshToken: String): AuthResponse {
        val requestBody = FormBody.Builder()
            .add("refresh_token", refreshToken)
            .build()

        val request = Request.Builder()
            .url("https://api.example.com/auth/refresh")
            .post(requestBody)
            .build()

        return makeRequest(request)
    }

    private suspend inline fun <reified T> makeRequest(request: Request): T {
        if (isTokenExpired() && !isRefreshing) {
            Log.d("correction", "Yes we fetch a new one ")
            isRefreshing = true
            fetchAuthToken()
            isRefreshing = false
        }
        Log.d("correction", "no keep going")

        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()

        if (response.isSuccessful) {
            if (responseBody != null) {
                try {
                    val type = object : TypeToken<T>() {}.type
                    return Gson().fromJson(responseBody, type)
                } catch (e: Exception) {
                    throw Exception("Erreur de parsing : ${e.message}")
                }
            } else {
                throw Exception("Le corps de la réponse est vide")
            }
        } else {
            throw Exception("Erreur lors de la requête API : ${response.code}")
        }
    }
}
