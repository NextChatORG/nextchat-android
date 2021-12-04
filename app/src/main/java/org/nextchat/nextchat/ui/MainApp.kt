package org.nextchat.nextchat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.nextchat.nextchat.ui.screens.Navigator
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.screens.general.HomeScreen
import org.nextchat.nextchat.ui.screens.general.SplashScreen
import org.nextchat.nextchat.ui.screens.index.SignInScreen

@Composable
fun MainApp() {
    // Hooks
    val navController = Navigator.current

    // Content
    NavHost(
        navController,
        startDestination = Screens.Splash.route,
    ) {
        // General screens
        composable(Screens.Splash.route) {
            SplashScreen()
        }

        composable(Screens.Home.route) {
            HomeScreen()
        }

        // Index screens
        composable(Screens.SignIn.route) {
            SignInScreen()
        }
    }
}
