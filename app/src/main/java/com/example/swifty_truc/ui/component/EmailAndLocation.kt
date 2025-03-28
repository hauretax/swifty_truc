package com.example.swifty_truc.ui.component

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import UserDTO
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun EmailAndLocation(user: UserDTO) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
        Text(
            text = user.location ?: "pas loguer dans l'ecole",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}