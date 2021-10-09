package org.nextchat.nextchat.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.nextchat.nextchat.constants.AccountStorage
import org.nextchat.nextchat.constants.StorageKeys
import org.nextchat.nextchat.ui.screens.Screens

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    accountStorage: AccountStorage,
    navController: NavController
) {
    // States
    var username by remember { mutableStateOf("No username") }

    // Hooks
    val homeScope = rememberCoroutineScope()

    // Coroutines
    homeScope.launch {
        val preferences = accountStorage.data.first()
        val uname = preferences[StorageKeys.accountUsername]

        withContext(Dispatchers.Main) {
            username = uname ?: ""
        }
    }


    // Content
    Scaffold {
        Column {
            Text(text = "Username: $username")
            Button(
                onClick = {
                    homeScope.launch {
                        accountStorage.edit { preferences ->
                            preferences.clear()
                        }

                        navController.navigate(route = Screens.Splash.route)
                    }
                }
            ) {
                Text("Sign out")
            }
        }
    }
}
