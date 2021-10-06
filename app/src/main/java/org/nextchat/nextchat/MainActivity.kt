package org.nextchat.nextchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.nextchat.nextchat.screens.index.SignUpScreen
import org.nextchat.nextchat.screens.index.WelcomeScreen
import org.nextchat.nextchat.ui.theme.NextChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NextChatTheme {
                NavHost(navController = navController, startDestination = "welcome") {
                    // Index screens
                    composable("welcome") { WelcomeScreen(navController) }
                    composable("sign_up") { SignUpScreen() }
                }
            }
        }
    }
}
