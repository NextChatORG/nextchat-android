package org.nextchat.nextchat

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import org.nextchat.nextchat.repositories.RepositoriesManager
import org.nextchat.nextchat.ui.MainApp
import org.nextchat.nextchat.ui.theme.NextChatTheme

class MainActivity : ComponentActivity() {
    private val Context.accountStorage by preferencesDataStore(name = "account_storage")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Content
        setContent {
            // Hooks
            val navController = rememberNavController()

            // Variables
            val repositories = remember {
                RepositoriesManager(
                    accountStorage = accountStorage,
                    navController = navController,
                )
            }

            // Content
            NextChatTheme {
                MainApp(
                    accountStorage = accountStorage,
                    navController = navController,
                    repositoriesManager = repositories,
                )
            }
        }
    }

}
