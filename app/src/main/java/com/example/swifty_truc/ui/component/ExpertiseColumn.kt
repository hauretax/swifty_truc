package com.example.swifty_truc.ui.component

import androidx.compose.runtime.*
import ExpertiseUser
import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.swifty_truc.dTO.ExpertiseDTO
import com.google.gson.Gson
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpertiseColumn(expertises: List<ExpertiseUser>) {
    val context = LocalContext.current
    var expertisesLists by remember { mutableStateOf<List<ExpertiseDTO>?>(null) }

    LaunchedEffect(Unit) {
        expertisesLists = getStoredExpertiseList(context)
    }
    Column {
        Text(
            text = "Expertises:",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Box(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .border(1.dp, Color(0xFF003366))
                .padding(5.dp)
        ) {
            LazyColumn {
                item {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        expertises.forEach { expertise ->
                            val expertiseItem =
                                expertisesLists?.find { it.id == expertise.expertiseId }
                            expertiseItem?.let {
                                ExpertiseChip(expertiseItem.name, expertise.value)
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun ExpertiseChip(name: String, value: Int) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF003366),
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$name: $value",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


fun getStoredExpertiseList(context: Context): List<ExpertiseDTO>? {
    val sharedPreferences = context.getSharedPreferences("expertise_prefs", Context.MODE_PRIVATE)
    val json = sharedPreferences.getString("expertise_list", null)

    return if (json != null) {
        Gson().fromJson(json, Array<ExpertiseDTO>::class.java).toList()
    } else {
        null
    }
}