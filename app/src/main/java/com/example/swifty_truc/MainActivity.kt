package com.example.swifty_truc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swifty_truc.ui.theme.Swifty_trucTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.swifty_truc.utils.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Swifty_trucTheme {
                val navController = rememberNavController()

                val sharedViewModel: SharedViewModel = viewModel()

                NavHost(navController = navController, startDestination = "find") {

                    composable("find") { FindScreen(navController, sharedViewModel) }
                    composable("show") { UserScreen(navController, sharedViewModel) }
                }
            }
        }
    }
}
