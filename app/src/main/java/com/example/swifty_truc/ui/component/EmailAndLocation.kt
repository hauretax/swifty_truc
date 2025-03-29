package com.example.swifty_truc.ui.component

import androidx.compose.runtime.*
import UserDTO
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun EmailAndLocation(user: UserDTO) {
    Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
    Text(
        text = user.location ?: "pas loguer dans l'ecole",
        style = MaterialTheme.typography.bodyMedium
    )
}