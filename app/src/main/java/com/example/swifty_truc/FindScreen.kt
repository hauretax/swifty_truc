package com.example.swifty_truc

import ApiService
import AuthResponse
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swifty_truc.utils.Todo
import com.example.swifty_truc.utils.fetchTodo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

@Composable
fun FindScreen() {
    val apiService = remember { ApiService(OkHttpClient()) }
    var authResponse by remember { mutableStateOf<AuthResponse?>(null) }
    var todo by remember { mutableStateOf<Todo?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        isLoading = true
        try {
            val rawResponse = withContext(Dispatchers.IO) { apiService.fetchAuthToken() }
            Log.d("FindScreen", "Réponse brute de l'API : $rawResponse")
            authResponse = rawResponse
            Log.d("FindScreen", "Token récupéré avec succès : $authResponse")
            Log.d("FindScreen", "Token récupéré avec succès : $authResponse")
        } catch (e: Exception) {
            Log.e("FindScreen", "Erreur lors de la récupération du todo : ${e.message}")
        } finally {
            isLoading = false
            if (authResponse != null) {
                Log.d("MainActivity", "Token récupéré avec succès : $authResponse")
            } else {
                Log.d("MainActivity", "L'appel API a échoué, pas de réponse.")
            }
        }
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                fetch(coroutineScope, { isLoading = it }, { todo = it })
            }
        ) {
            Text(text = "Charger un to-do")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (todo != null) {
            Text(text = "ID: ${todo!!.id}")
            Text(text = "Titre: ${todo!!.title}")
            Text(text = "Complété: ${if (todo!!.completed) "Oui" else "Non"}")
        } else {
            Text(text = "Clique sur le bouton pour charger un to-do.")
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFindScreen() {
    FindScreen()
}

fun fetch(coroutineScope: CoroutineScope, onLoadingChange: (Boolean) -> Unit, onTodoChange: (Todo?) -> Unit) {
    onLoadingChange(true)
    onTodoChange(null)

    coroutineScope.launch {
        try {
            val todo = fetchTodo()
            onTodoChange(todo)
        } catch (e: Exception) {
            Log.e("FindScreen", "Erreur lors de la récupération du todo : ${e.message}")
        } finally {
            onLoadingChange(false)
        }
    }
}