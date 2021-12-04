package org.nextchat.nextchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import org.nextchat.nextchat.ui.MainApp
import org.nextchat.nextchat.ui.screens.NavigatorProvider
import org.nextchat.nextchat.ui.theme.NextChatTheme
import org.nextchat.nextchat.ui.viewmodels.ViewModelsProvider

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Content
        setContent {
            NavigatorProvider {
                ViewModelsProvider {
                    NextChatTheme {
                        MainApp()
                    }
                }
            }
        }
    }
}
