package com.example.swifty_truc.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import UserDTO
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
        Text(
            text = "Level: ${user.cursusUsers.firstOrNull()?.level ?: "N/A"}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
