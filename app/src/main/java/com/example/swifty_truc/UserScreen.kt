package com.example.swifty_truc

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.swifty_truc.dTO.EventDTO
import com.example.swifty_truc.utils.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import com.example.swifty_truc.services.ApiService

@Composable
fun UserScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val user = sharedViewModel.user
    val apiService = sharedViewModel.apiService
    val coroutineScope = rememberCoroutineScope()
    val eventsState = remember { mutableStateOf<List<EventDTO>?>(null) }

    LaunchedEffect(user) {
        user?.let {
            if(apiService != null)
            fetchEvents(coroutineScope, apiService, it.id, eventsState)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (user != null) {
            Text(text = "Utilisateur: ${user.email}")
            val events = eventsState.value
            if (!events.isNullOrEmpty()) {
                Text(text = "Événements associés: ${events[0].name}")
            }
        } else {
            Text(text = "Erreur : données manquantes.")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Retour à Find")
        }
    }
}


fun fetchEvents(
    coroutineScope: CoroutineScope,
    apiService: ApiService,
    userId: Int,
    eventsState: MutableState<List<EventDTO>?>
) {
    coroutineScope.launch {
        try {
            val events = withContext(Dispatchers.IO) { apiService.fetchEventsUserData(userId) }
            Log.d("events : ", events.size.toString())

            eventsState.value = events
        } catch (e: Exception) {
            Log.e("FindScreen", "Erreur lors d un truc : ${e.message}")
        } finally {
        }
    }
}