package com.example.swifty_truc

import UserDTO
import com.example.swifty_truc.services.ApiService
import com.example.swifty_truc.dTO.AuthResponse
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swifty_truc.dTO.EventDTO
import com.example.swifty_truc.dTO.EventsDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

@Composable
fun FindScreen() {
    val apiService = remember { ApiService(OkHttpClient()) }
    var authResponse by remember { mutableStateOf<AuthResponse?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        isLoading = true
        try {
            val rawResponse = withContext(Dispatchers.IO) { apiService.fetchAuthToken() }
            authResponse = rawResponse
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
                fetch(coroutineScope,apiService)
            }
        ) {
            Text(text = "Charger un to-do")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (null != null) {
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

fun fetch(coroutineScope: CoroutineScope,apiService: ApiService) {


    coroutineScope.launch {
        try {
            val user : UserDTO =  withContext(Dispatchers.IO) {apiService.fetchUserData()}
            Log.d("test", user.wallet.toString())
            val eventsDTO=  withContext(Dispatchers.IO) {apiService.fetchEventsUserData()}
            Log.d("events : ", eventsDTO.size.toString())
        } catch (e: Exception) {
            Log.e("FindScreen", "Erreur lors d un truc : ${e.message}")
        } finally {
        }
    }
}