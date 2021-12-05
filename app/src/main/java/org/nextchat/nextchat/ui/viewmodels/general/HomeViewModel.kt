package org.nextchat.nextchat.ui.viewmodels.general

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.nextchat.nextchat.data.datastore.SessionDataStore
import org.nextchat.nextchat.ui.screens.Screens
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionDataStore: SessionDataStore
): ViewModel() {
    // States
    var needRedirect by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    // Effects
    init {
        viewModelScope.launch {
            val sessionData = sessionDataStore.getData()

            if (sessionData == null) {
                sessionDataStore.clearData()
                needRedirect = Screens.Splash.route
            } else {
                username = sessionData.username
            }
        }
    }

    // Handlers
    fun signOut() {
        viewModelScope.launch {
            sessionDataStore.clearData()
            needRedirect = Screens.Splash.route
        }
    }
}
