package org.nextchat.nextchat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import org.nextchat.nextchat.constants.AccountStorage
import org.nextchat.nextchat.repositories.RepositoriesManager
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.screens.general.SplashScreen
import org.nextchat.nextchat.ui.screens.home.HomeScreen
import org.nextchat.nextchat.ui.screens.index.RecoveryCodesScreen
import org.nextchat.nextchat.ui.screens.index.SignUpScreen
import org.nextchat.nextchat.ui.screens.index.WelcomeScreen

@Composable
fun MainApp(
    accountStorage: AccountStorage,
    navController: NavHostController,
    repositoriesManager: RepositoriesManager,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
    ) {
        // General screens
        composable(route = Screens.Splash.route) {
            SplashScreen(
                accountStorage = accountStorage,
                navController = navController
            )
        }

        // Index screens
        composable(route = Screens.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screens.SignUp.route) {
            SignUpScreen(
                navController = navController,
                signUpRepository = repositoriesManager.signUp,
            )
        }
        composable(
            route = Screens.RecoveryCodes.route + "/{codes}",
            arguments = listOf(navArgument("codes") { type = NavType.StringType })
        ) { entry ->
            if (entry.arguments != null) {
                RecoveryCodesScreen(
                    codes = entry.arguments!!.getString("codes")!!.split(";"),
                    navController = navController,
                )
            }
        }

        // Home screens
        composable(route = Screens.Home.route) {
            HomeScreen(
                accountStorage = accountStorage,
                navController = navController
            )
        }
    }
}
