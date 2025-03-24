package com.example.swifty_truc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Show(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        Text(text = "C'est l'écran Show")
        Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
            Text(text = "Retour à Find")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    Show(navController = rememberNavController())
}
