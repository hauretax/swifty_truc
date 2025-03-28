package com.example.swifty_truc.ui.component

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import ExpertiseUser
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun ExpertiseColumn(expertises: List<ExpertiseUser>) {
    Column {
        Text(text = "Expertises:", style = MaterialTheme.typography.headlineMedium)
        expertises.forEach { expertise ->
            Text(
                text = "Expertise Value: ${expertise.value}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}