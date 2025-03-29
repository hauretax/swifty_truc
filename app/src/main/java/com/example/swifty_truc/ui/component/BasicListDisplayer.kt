package com.example.swifty_truc.ui.component

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

data class DisplayElements(val text: String, val color: Color? = null, val description:String?)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicListDisplayer(elements: List<DisplayElements>, title: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 18.sp, color = Color.Gray
        )
        Text(
            text = elements[0].description ?: "", //je sait je cree 10 fois la meme description ... je voulais juste tester un visuelle je lest trouver joli il reste deso les perfes
            fontSize = 15.sp, color = Color.Gray
        )
        LazyColumn {
            item {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    elements.forEach { element ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color =  element.color ?: Color(0xFF003366),
                            modifier = Modifier.padding(4.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                text = element.text,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )

                        }
                    }

                }
            }
        }
    }
}
