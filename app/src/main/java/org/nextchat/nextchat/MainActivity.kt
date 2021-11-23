package org.nextchat.nextchat

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import org.nextchat.nextchat.models.index.SignInViewModel
import org.nextchat.nextchat.ui.MainApp
import org.nextchat.nextchat.ui.theme.NextChatTheme

class MainActivity : ComponentActivity() {
    private val Context.accountStorage by preferencesDataStore(name = "account_storage")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        // View models
        val signInViewModel by viewModels<SignInViewModel>()

        // Content
        setContent {
            // Hooks
            val navController = rememberNavController()

            // Content
            NextChatTheme {
                MainApp(
                    accountStorage = accountStorage,
                    navController = navController,

                    // View models
                    signInViewModel = signInViewModel
                )
            }
        }
    }

}
