package com.example.swifty_truc.services

import UserDTO
import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import android.util.Base64
import android.util.Log
import com.example.swifty_truc.dTO.AuthResponse
import com.example.swifty_truc.dTO.EventDTO
import com.example.swifty_truc.dTO.EventsDTO

class ApiService(private val client: OkHttpClient) {

    private var token: String = ""

    suspend fun fetchAuthToken(): AuthResponse {
        val clientId = "u-s4t2ud-fc28b4a661a126ce7936bc7147395db5a2038f184c771450a417ee8c256e2094"
        val clientSecret =
            "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"

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
        val t: AuthResponse = makeRequest(request)
        token = t.accessToken

        return t
    }

    suspend fun fetchUserData(): UserDTO {
        val request = Request.Builder()
            .url("https://api.intra.42.fr/v2/users/hutricot")
            .header("Authorization", "Bearer $token")
            .build()

        return makeRequest(request)
    }

    suspend fun fetchEventsUserData(): EventsDTO {
        val request = Request.Builder()
            .url("https://api.intra.42.fr/v2/users/37164/events")
            .header("Authorization", "Bearer $token")
            .build()

        return makeRequest(request)
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
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
        if (responseBody != null) {
            Log.d("response", responseBody)
        }
        if (response.isSuccessful) {
            if (responseBody != null) {
                return Gson().fromJson(responseBody, T::class.java)
            } else {
                throw Exception("Le corps de la réponse est vide")
            }
        } else {
            throw Exception("Erreur lors de la requête API : ${response.code}")
        }
    }
}
