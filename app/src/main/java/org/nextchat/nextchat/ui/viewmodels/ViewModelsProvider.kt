package org.nextchat.nextchat.ui.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import org.nextchat.nextchat.ui.viewmodels.general.SplashViewModel

object ViewModels {
    // General
    val splash: SplashViewModel
        @Composable
        get() = LocalSplashViewModel.current

    // Index
}

@Composable
fun ViewModelsProvider(content: @Composable () -> Unit) {
    // General
    val splash: SplashViewModel = viewModel()

    // Content
    CompositionLocalProvider(
        LocalSplashViewModel provides splash,
        content = content
    )
}

private val LocalSplashViewModel = staticCompositionLocalOf<SplashViewModel> {
    error("No SplashViewModel provided")
}
