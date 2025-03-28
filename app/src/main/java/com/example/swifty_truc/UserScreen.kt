package com.example.swifty_truc

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swifty_truc.dTO.EventDTO
import com.example.swifty_truc.utils.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import com.example.swifty_truc.services.ApiService
import com.example.swifty_truc.ui.component.EmailAndLocation
import com.example.swifty_truc.ui.component.EventListColumn
import com.example.swifty_truc.ui.component.ExpertiseColumn
import com.example.swifty_truc.ui.component.ProfilePhoto
import com.example.swifty_truc.ui.component.UserInfo


@Composable
fun UserScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val user = sharedViewModel.user
    val apiService = sharedViewModel.apiService
    val coroutineScope = rememberCoroutineScope()
    val eventsState = remember { mutableStateOf<List<EventDTO>?>(null) }

    LaunchedEffect(user) {
        user?.let {
            if (apiService != null)
                fetchEvents(coroutineScope, apiService, it.id, eventsState)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user != null) {
            ProfilePhoto(user.image.link)

            UserInfo(user = user)
            EmailAndLocation(user)
            val events = eventsState.value
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ExpertiseColumn(expertises = user.expertisesUsers)
                if (!events.isNullOrEmpty()) {
                EventListColumn(events)
                } else {
                    Text(text = "Aucun événement disponible.", fontSize = 16.sp, color = Color.Gray)
                }
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