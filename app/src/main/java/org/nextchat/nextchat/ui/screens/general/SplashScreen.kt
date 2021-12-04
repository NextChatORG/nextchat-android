package org.nextchat.nextchat.ui.screens.general

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import org.nextchat.nextchat.ui.viewmodels.ViewModels

@Composable
fun SplashScreen() {
    // Hooks
    val viewModel = ViewModels.splash

    // Content
    Scaffold(
        contentColor = Color.White
    ) {
        // Loading Content
        if (viewModel.loading) {
            CircularProgressIndicator()
        }
        // Content
        else {
            Text(text = "Ruta a entrar: ${viewModel.redirectTo}", style = TextStyle(color = Color.White))
        }
    }
}
