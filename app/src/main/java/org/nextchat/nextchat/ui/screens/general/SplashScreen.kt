package org.nextchat.nextchat.ui.screens.general

import android.annotation.SuppressLint
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.nextchat.nextchat.constants.AccountStorage
import org.nextchat.nextchat.constants.StorageKeys
import org.nextchat.nextchat.ui.screens.Screens

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    accountStorage: AccountStorage,
    navController: NavController
) {
    // States
    var accessToken by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(true) }

    // Hooks
    val scope = rememberCoroutineScope()

    // Effects
    scope.launch {
        val preferences = accountStorage.data.first()
        val token = preferences[StorageKeys.accountToken]

        accessToken = token
        loading = false
    }

    // Loading content
    if (loading) {
        Text(text = "Loading data...")
        return
    }

    val route = if (accessToken == null) {
        Screens.Welcome.route
    } else {
        Screens.Home.route
    }

    navController.navigate(route = route) {
        popUpTo(route = route) {
            inclusive = true
        }
    }
}
