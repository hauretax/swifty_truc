package com.example.swifty_truc.utils

import UserDTO
import androidx.compose.ui.graphics.Color
import com.example.swifty_truc.ui.component.DisplayElements

fun createProjetsDisplayList(user: UserDTO): List<DisplayElements> {
    val projets = user.projectsUsers

    val averageProjetsFinaleMark = user.projectsUsers
        .mapNotNull { it.finalMark?.takeIf { mark -> mark > 0 } }
        .average()
        .toInt()

    return projets.map { projet ->
        val color = when (projet.validated) {
            true -> Color(0xFF66BB6A)
            false -> Color.Red
            else -> Color.Gray
        }
        DisplayElements(
            text = "${projet.project.name}: ${projet.finalMark}",
            color = color,
            "$averageProjetsFinaleMark% en moyenne"
        )
    }
}