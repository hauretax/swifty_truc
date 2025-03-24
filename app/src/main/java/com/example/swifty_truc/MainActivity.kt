package com.example.swifty_truc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swifty_truc.ui.theme.Swifty_trucTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Swifty_trucTheme {
                // Récupère un contrôleur de navigation
                val navController = rememberNavController()
                Button(onClick = { navController.navigate("find") }) {
                    Text(text = "Aller à Show")
                }
                // Crée la NavHost qui va gérer la navigation
                NavHost(navController = navController, startDestination = "find") {

                    composable("find") { FindScreen() }
                    composable("show") {
                        Show(navController = navController)
                    }
                }
            }
        }
    }
}
