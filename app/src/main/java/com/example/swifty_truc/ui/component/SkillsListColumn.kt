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
import Skill
import UserDTO
import androidx.compose.ui.Alignment

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsListColumn(user: UserDTO) {

    val cursus = user.cursusUsers.find { it.cursusId == 21 }
    if (cursus?.hasCoalition == true) {
        var skills = cursus.skills

        Column {
            Text(
                text = "Skills : ",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Box(
                modifier = Modifier
                    .border(1.dp, Color(0xFF003366))
                    .padding(3.dp)
            ) {

                LazyColumn {
                    item {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            skills.forEach { skill ->
                                skillChip(skill.name, skill.level)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun skillChip(name: String, value: Double) {
    val percentage = value / 20 * 100
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
                text = "$name: $value ou $percentage%",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}