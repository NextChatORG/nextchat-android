package org.nextchat.nextchat.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

object Navigator {
    val current: NavHostController
        @Composable
        get() = LocalNavHostController.current
}

@Composable
fun NavigatorProvider(content: @Composable () -> Unit) {
    // Hooks
    val navController = rememberNavController()

    // Content
    CompositionLocalProvider(
        LocalNavHostController provides navController,
        content = content
    )
}

private val LocalNavHostController = staticCompositionLocalOf<NavHostController> {
    error("No NavHostController provided")
}
