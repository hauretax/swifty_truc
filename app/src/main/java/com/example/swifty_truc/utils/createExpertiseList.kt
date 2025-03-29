package com.example.swifty_truc.utils

import androidx.compose.runtime.*
import ExpertiseUser
import android.content.Context
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.swifty_truc.dTO.ExpertiseDTO
import com.example.swifty_truc.ui.component.DisplayElements
import com.google.gson.Gson

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun createExpertiseDisplayList(expertises: List<ExpertiseUser>): List<DisplayElements> {
    val context = LocalContext.current
    var expertisesLists by remember { mutableStateOf<List<ExpertiseDTO>?>(null) }

    expertisesLists = getStoredExpertiseList(context)

    val expertiseList = expertises.mapNotNull { expertise ->
        val expertiseItem = expertisesLists?.find { it.id == expertise.expertiseId }
        expertiseItem?.let {
            DisplayElements(text = "${it.name}: ${expertise.value}", color = Color(0xffffc21c), "t as vu c est doree")
        }
    }
    return expertiseList;
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