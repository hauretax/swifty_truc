package com.example.swifty_truc.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import UserDTO
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun UserInfo(user: UserDTO) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = user.login,
            style = MaterialTheme.typography.headlineLarge
        )
        val cursus = user.cursusUsers.find { it.cursusId == 21 }
        if (cursus?.hasCoalition == true) {
            Text(
                text = cursus.level.toString(),
                style = MaterialTheme.typography.headlineLarge
            )
        } else {
            Text(
                text = "Tu n'as pas de coalition ???! ",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}
