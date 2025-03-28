package com.example.swifty_truc

import UserDTO
import com.example.swifty_truc.services.ApiService
import com.example.swifty_truc.dTO.AuthResponse
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swifty_truc.utils.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

@Composable
fun FindScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val apiService = remember { ApiService(OkHttpClient()) }
    var authResponse by remember { mutableStateOf<AuthResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var inputText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        sharedViewModel.apiService = apiService
        try {
            val rawResponse = withContext(Dispatchers.IO) { apiService.fetchAuthToken() }
            authResponse = rawResponse
        } catch (e: Exception) {
            Log.e("FindScreen", "Erreur lors de la récupération du todo : ${e.message}")
        } finally {
            if (authResponse != null) {
                Log.d("MainActivity", "Token récupéré avec succès : $authResponse")
            } else {
                Log.d("MainActivity", "L'appel API a échoué, pas de réponse.")
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Entrer un utilisateur") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                fetch(coroutineScope, apiService, inputText, navController, sharedViewModel)
            }
        ) {
            Text(text = "Charger soldat !")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "Selectionner un Utilisqteur et bim bam boum.")


    }
}

fun fetch(
    coroutineScope: CoroutineScope,
    apiService: ApiService,
    userName: String,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    coroutineScope.launch {
        try {
            val user: UserDTO = withContext(Dispatchers.IO) { apiService.fetchUserData(userName) }
            Log.d("test", user.wallet.toString())
            sharedViewModel.user = user
            navController.navigate("show")
        } catch (e: Exception) {
            Log.e("FindScreen", "Erreur lors d un truc : ${e.message}")
        } finally {
        }
    }
}