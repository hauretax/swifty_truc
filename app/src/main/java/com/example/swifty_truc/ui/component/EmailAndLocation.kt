package com.example.swifty_truc.ui.component

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import UserDTO
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun EmailAndLocation(user: UserDTO) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Location: ${user.location ?: "Not Available"}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}