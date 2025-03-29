package com.example.swifty_truc.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.swifty_truc.dTO.EventDTO

@Composable
fun EventListColumn(eventList: List<EventDTO>) {
    Column {
        Text(
            text = "Events : ",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 10.dp)
        )


            LazyColumn {
                items(eventList) { event ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF003366),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(
                            text = event.name,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )

                }

            }
        }
    }
}