package com.example.swifty_truc

import UserDTO
import com.example.swifty_truc.services.ApiService
import com.example.swifty_truc.dTO.AuthResponse
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {

        sharedViewModel.apiService = apiService
        try {
            val rawResponse = withContext(Dispatchers.IO) { apiService.fetchAuthToken() }
            authResponse = rawResponse
        } catch (e: Exception) {
            Log.e("FindScreen", "Erreur lors de la récupération du token  : ${e.message}")
        } finally {
            if (authResponse != null) {
                withContext(Dispatchers.IO) { apiService.fetchExpertiseList(context) }
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
        Image(
            painter = painterResource(id = R.drawable.logo42),
            contentDescription = "Logo de l ecole 42",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Entrer un utilisateur") },
            modifier = Modifier.fillMaxWidth()
        )
        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error, // Le texte en rouge
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally)
            )
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                errorMessage = null
                fetch(coroutineScope, apiService, inputText, navController, sharedViewModel, { errorMessage = it })

            }

        ) {
            Text(text = "Charger soldat !")
        }



    }
}

fun fetch(
    coroutineScope: CoroutineScope,
    apiService: ApiService,
    userName: String,
    navController: NavController,
    sharedViewModel: SharedViewModel,
    onError: (String) -> Unit
) {
    coroutineScope.launch {
        try {
            val user: UserDTO = withContext(Dispatchers.IO) { apiService.fetchUserData(userName) }
            sharedViewModel.user = user
            navController.navigate("show")
        } catch (e: Exception) {

            Log.e("FindScreen", "Erreur lors d un truc : ${e.message}")
            if (e.message?.contains("404") == true) {
                onError("Utilisateur non trouvé")
            } else {
                onError("Erreur lors de la récupération des données")
            }
        } finally {
        }
    }
}