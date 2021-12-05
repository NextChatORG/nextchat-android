package org.nextchat.nextchat.ui.viewmodels.general

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.nextchat.nextchat.domain.repositories.AuthRepository
import org.nextchat.nextchat.ui.screens.Screens
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    // States
    var loading by mutableStateOf(false)
        private set

    var redirectTo by mutableStateOf("")
        private set

    // Effects
    init {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                loading = true
            }
        }, 250)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        viewModelScope.launch {
                            redirectTo = if (authRepository.isLogged()) {
                                Screens.Home.route
                            } else {
                                Screens.SignIn.route
                            }
                        }
                    }
                }, 500)

                loading = false
            }
        }, 2000)
    }
}
