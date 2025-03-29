package com.example.swifty_truc

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swifty_truc.dTO.EventDTO
import com.example.swifty_truc.utils.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import com.example.swifty_truc.services.ApiService
import com.example.swifty_truc.ui.component.BasicListDisplayer
import com.example.swifty_truc.ui.component.EmailAndLocation
import com.example.swifty_truc.ui.component.ProfilePhoto
import com.example.swifty_truc.ui.component.UserInfo
import com.example.swifty_truc.utils.createExpertiseDisplayList
import com.example.swifty_truc.utils.createEventDisplayList
import com.example.swifty_truc.utils.createSkillDisplayList

@Composable
fun UserScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val user = sharedViewModel.user
    val apiService = sharedViewModel.apiService
    val coroutineScope = rememberCoroutineScope()
    val eventsState = remember { mutableStateOf<List<EventDTO>?>(null) }
    var currentTab by remember { mutableStateOf("skills") }

    LaunchedEffect(user) {
        user?.let {
            if (apiService != null)
                fetchEvents(coroutineScope, apiService, it.id, eventsState)
        }
    }
    Text(
        text = "Retour à Find",
        modifier = Modifier
            .clickable { navController.popBackStack() }
            .padding(16.dp)
            .padding(bottom = 16.dp),
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline
    )
    Column(
        modifier = Modifier
            .padding(8.dp)
            .padding(top = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user != null) {

            Row {
                ProfilePhoto(user.image.link)
                Column {
                    UserInfo(user = user)
                    EmailAndLocation(user)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { currentTab = "skills" },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Skills")
                }
                Button(
                    onClick = { currentTab = "expertises" },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Expertises")
                }
                Button(
                    onClick = { currentTab = "events" },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Events")
                }
                Button(
                    onClick = { currentTab = "projets" },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Projets")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val events = eventsState.value
            when (currentTab) {
                "skills" -> BasicListDisplayer(createSkillDisplayList(user), "Skills")
                "expertises" -> BasicListDisplayer(
                    createExpertiseDisplayList(user.expertisesUsers),
                    "Expertise"
                )
                "events" -> BasicListDisplayer(createEventDisplayList(events), "Event")
                "projets" -> Text("Bientôt projets ici", fontSize = 18.sp, color = Color.Gray)
            }

        } else {
            Text(text = "Erreur : données manquantes.")
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