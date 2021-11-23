package org.nextchat.nextchat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.nextchat.nextchat.constants.AccountStorage
import org.nextchat.nextchat.models.index.SignInViewModel
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.screens.general.HomeScreen
import org.nextchat.nextchat.ui.screens.general.SplashScreen
import org.nextchat.nextchat.ui.screens.index.SignInScreen

@Composable
fun MainApp(
    accountStorage: AccountStorage,
    navController: NavHostController,

    // View models
    signInViewModel: SignInViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignIn.route,
    ) {
        // General screens
        composable(Screens.Splash.route) {
            SplashScreen(accountStorage, navController)
        }

        composable(Screens.Home.route) {
            HomeScreen(accountStorage, navController)
        }

        // Index screens
        composable(Screens.SignIn.route) {
            SignInScreen(accountStorage, navController, signInViewModel)
        }
    }
}
