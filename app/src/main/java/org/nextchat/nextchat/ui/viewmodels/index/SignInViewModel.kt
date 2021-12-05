package org.nextchat.nextchat.ui.viewmodels.index

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.nextchat.nextchat.domain.repositories.AuthRepository
import org.nextchat.nextchat.ui.viewmodels.BaseViewModel
import org.nextchat.nextchat.utils.getErrorTextByKey
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository
): BaseViewModel(application) {
    companion object {
        private const val TAG = "SIGN_IN_VIEW_MODEL"
    }

    // States
    var loading by mutableStateOf(false)
        private set

    var username by mutableStateOf("")
        private set

    var usernameError by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    // Handlers
    fun handleUsernameChange(): (String) -> Unit {
        return { value ->
            username = value
            usernameError = ""
        }
    }

    fun handlePasswordChange(): (String) -> Unit {
        return { value ->
            password = value
            passwordError = ""
        }
    }

    fun submit(
        onSuccess: () -> Unit
    ) {
        if (username.isEmpty()) {
            usernameError = getErrorTextByKey(context, "AUTH_EMPTY_USERNAME")
            return
        } else if (password.isEmpty()) {
            passwordError = getErrorTextByKey(context, "AUTH_EMPTY_PASSWORD")
            return
        }

        loading = true

        usernameError = ""
        passwordError = ""

        viewModelScope.launch {
            Log.i(TAG, "Sending sign-in data")

            val result = authRepository.signIn(username, password)
            if (result.isSuccess()) {
                withContext(Dispatchers.Main) {
                    Log.i(TAG, "Logged successfully")
                    onSuccess()
                }
            } else {
                val error = result.getErrorOrNull()
                if (error != null) {
                    val fieldError = error.fieldError
                    if (fieldError != null) {
                        val message = getErrorTextByKey(context, fieldError.key)

                        if (fieldError.field == "username") {
                            usernameError = message
                        } else if (fieldError.field == "password") {
                            passwordError = message
                        }
                    }
                }
            }
        }

        loading = false
    }
}
