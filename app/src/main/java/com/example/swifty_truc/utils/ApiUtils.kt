package com.example.swifty_truc.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

data class Todo(
    val id: Int,
    val title: String,
    val completed: Boolean
)

suspend fun fetchTodo(): Todo? {
    return withContext(Dispatchers.IO) {
        val apiUrl = "https://jsonplaceholder.typicode.com/todos/1"
        var result: Todo? = null

        try {
            Log.d("fetchTodo", "Début de la requête à l'API: $apiUrl") // ✅ Log de début

            val url = URL(apiUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            val responseCode = connection.responseCode
            Log.d("fetchTodo", "Code de réponse HTTP: $responseCode")

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.readText()
                reader.close()

                Log.d("fetchTodo", "Réponse de l'API: $response")

                val json = JSONObject(response)
                result = Todo(
                    id = json.getInt("id"),
                    title = json.getString("title"),
                    completed = json.getBoolean("completed")
                )
                Log.d("fetchTodo", "Objet Todo créé: $result")
            } else {
                Log.e("fetchTodo", "Erreur HTTP : $responseCode")
            }

            connection.disconnect()
        } catch (e: Exception) {
            Log.e("fetchTodo", "Exception : ${e.message}", e)
        }
        result
    }
}
