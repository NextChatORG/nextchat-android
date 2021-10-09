package org.nextchat.nextchat

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.nextchat.nextchat.core.Screens
import org.nextchat.nextchat.screens.home.HomeScreen
import org.nextchat.nextchat.screens.index.RecoveryCodesScreen
import org.nextchat.nextchat.screens.index.SignUpScreen
import org.nextchat.nextchat.screens.index.WelcomeScreen
import org.nextchat.nextchat.ui.theme.NextChatTheme
import org.nextchat.nextchat.utils.storage_keys

class MainActivity : ComponentActivity() {
    private var accessToken: String? = null

    private val Context.accountStorage by preferencesDataStore(
        name = "account_storage"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Content
        setContent {
            // States
            var loading by remember { mutableStateOf(true) }

            // Hooks
            val navController = rememberNavController()
            val mainScope = rememberCoroutineScope()

            // Effects
            mainScope.launch {
                val preferences = accountStorage.data.first()
                val token = preferences[storage_keys.accountToken]

                withContext(Dispatchers.Main) {
                    accessToken = token
                    loading = false
                }
            }

            // Content
            NextChatTheme {
                if (loading) {
                    Text(text = "Loading data...")
                } else {
                    NavHost(
                        navController = navController,
                        startDestination = if (accessToken != null) { Screens.HomeScreen.route } else { Screens.WelcomeScreen.route }
                    ) {
                        // Index screens
                        composable(route = Screens.WelcomeScreen.route) {
                            WelcomeScreen(navController = navController)
                        }
                        composable(route = Screens.SignUpScreen.route) {
                            SignUpScreen(
                                accountStorage = accountStorage,
                                navController = navController,
                            )
                        }
                        composable(
                            route = Screens.RecoveryCodesScreen.route + "/{codes}",
                            arguments = listOf(navArgument("codes") { type = NavType.StringType })
                        ) { entry ->
                            if (entry.arguments != null) {
                                RecoveryCodesScreen(
                                    codes = entry.arguments!!.getString("codes")!!.split(";"),
                                    navController = navController
                                )
                            }
                        }

                        // Home screens
                        composable(route = Screens.HomeScreen.route) {
                            HomeScreen(
                                accountStorage = accountStorage,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
