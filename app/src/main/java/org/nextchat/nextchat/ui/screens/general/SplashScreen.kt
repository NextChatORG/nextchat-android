package org.nextchat.nextchat.ui.screens.general

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.nextchat.nextchat.ui.common.NextChatLogo
import org.nextchat.nextchat.ui.screens.Navigator
import org.nextchat.nextchat.ui.viewmodels.general.SplashViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel()
) {
    // Hooks
    val navController = Navigator.current

    // Redirect to
    if (!viewModel.loading && viewModel.redirectTo.isNotEmpty()) {
        navController.navigate(viewModel.redirectTo) {
            navController.clearBackStack(viewModel.redirectTo)
        }
    }

    // Content
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedLoader(viewModel.loading)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedLoader(
    visible: Boolean
) {
    // Logo animations
    val logoEnterTransition = fadeIn(
        animationSpec = tween(500)
    ) + slideInVertically(
        initialOffsetY = { -100 },
        animationSpec = tween(500)
    )

    val logoExitTransition = fadeOut(
        animationSpec = tween(500)
    ) + slideOutVertically(
        animationSpec = tween(500)
    )

    // Content
    AnimatedVisibility(
        visible = visible,
        enter = logoEnterTransition,
        exit = logoExitTransition,
    ) {
        NextChatLogo()
    }
}
