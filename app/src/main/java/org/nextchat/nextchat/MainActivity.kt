package org.nextchat.nextchat

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
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

            // Content
            NextChatTheme {
                MainApp(
                    accountStorage = accountStorage,
                    navController = navController
                )
            }
        }
    }

}
