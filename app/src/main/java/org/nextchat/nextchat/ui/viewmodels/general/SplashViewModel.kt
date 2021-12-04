package org.nextchat.nextchat.ui.viewmodels.general

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.nextchat.nextchat.domain.repositories.AuthRepository
import org.nextchat.nextchat.ui.screens.Screens
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    companion object {
        private const val TAG = "SPLASH_VIEW_MODEL"
    }

    var loading by mutableStateOf(true)
        private set

    var redirectTo by mutableStateOf(Screens.SignIn.route)
        private set

    init {
        Log.i(TAG, "Start splash view model")
        loading = true

        viewModelScope.launch {
            redirectTo = if (authRepository.isLogged()) {
                Screens.Home.route
            } else {
                Screens.SignIn.route
            }

            loading = false
        }

        Log.i(TAG, "Finish splash view model: $redirectTo - $loading")
    }
}
