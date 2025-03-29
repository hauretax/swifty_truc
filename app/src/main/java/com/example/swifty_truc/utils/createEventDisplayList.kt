package com.example.swifty_truc.utils
import com.example.swifty_truc.dTO.EventDTO
import com.example.swifty_truc.ui.component.DisplayElements

fun createEventDisplayList(events: List<EventDTO>?): List<DisplayElements>{

    if (events.isNullOrEmpty()) {
        return listOf(DisplayElements(text = "Pas d'événements trouvés"))
    }

    listOf(DisplayElements(text = "pas d'evenement trouver"))
    return events.map { event -> DisplayElements(text = event.name) }
}