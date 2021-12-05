package org.nextchat.nextchat.ui.screens.general

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import org.nextchat.nextchat.ui.screens.Navigator
import org.nextchat.nextchat.ui.viewmodels.general.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    // Hooks
    val navController = Navigator.current

    // Checks
    if (viewModel.needRedirect.isNotEmpty()) {
        navController.navigate(viewModel.needRedirect)
        return
    }

    // Content
    Scaffold {
        Column {
            Text("Usuario: ${viewModel.username}")

            Button(onClick = {
                viewModel.signOut()
            }) {
                Text(text = "Cerrar sesión")
            }
        }
    }
}
