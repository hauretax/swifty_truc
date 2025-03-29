package com.example.swifty_truc.utils

import UserDTO
import com.example.swifty_truc.ui.component.DisplayElements

fun createSkillDisplayList(user: UserDTO): List<DisplayElements> {
    val cursus = user.cursusUsers.find { it.cursusId == 21 }


    if (cursus?.hasCoalition == true) {
        var skills = cursus.skills
        val averageSkill = skills.map {
            it.level
        }.average().toInt()

        return  skills.map { skill ->
            val percentage = (skill.level / 20 * 100).toInt()
            DisplayElements(text = "${skill.name}: ${skill.level} ou $percentage%", null, "$averageSkill/20")
        }


    }
    return listOf(DisplayElements(text = "", null, "pas de skills trouvés"))
}