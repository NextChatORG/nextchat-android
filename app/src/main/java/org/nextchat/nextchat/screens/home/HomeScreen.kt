package org.nextchat.nextchat.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.nextchat.nextchat.core.Screens
import org.nextchat.nextchat.utils.storage_keys

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    accountStorage: DataStore<Preferences>,
    navController: NavController
) {
    // States
    var username by remember { mutableStateOf("No username") }

    // Hooks
    val homeScope = rememberCoroutineScope()

    // Coroutines
    homeScope.launch {
        val preferences = accountStorage.data.first()
        val uname = preferences[storage_keys.accountUsername]

        withContext(Dispatchers.Main) {
            username = uname ?: ""
        }
    }

    // Content
    Scaffold {
        Column {
            Text(
                text = "Username: $username"
            )
            Button(
                onClick = {
                    homeScope.launch {
                        accountStorage.edit { preferences ->
                            preferences.clear()
                        }

                        navController.navigate(route = Screens.WelcomeScreen.route) {
                            popUpTo(route = Screens.WelcomeScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            ) {
                Text("Sign out")
            }
        }
    }
}
