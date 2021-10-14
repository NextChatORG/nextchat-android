package org.nextchat.nextchat.ui.screens.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.nextchat.nextchat.ui.layouts.IndexLayout
import org.nextchat.nextchat.R
import org.nextchat.nextchat.repositories.index.SignUpRepository
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.widgets.FractionSpacer
import org.nextchat.nextchat.ui.widgets.ResourceText
import org.nextchat.nextchat.ui.widgets.TextFieldError

@Composable
fun SignUpScreen(
    navController: NavController,
    signUpRepository: SignUpRepository,
) {
    // States
    var username by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    var repeatPassword by remember { mutableStateOf("") }
    var repeatPasswordError by remember { mutableStateOf("") }

    var betaKey by remember { mutableStateOf("") }
    var betaKeyError by remember { mutableStateOf("") }

    // Content
    IndexLayout(title = R.string.sign_up_screen_title) {
        // Username input
        OutlinedTextField(
            isError = usernameError.isNotEmpty(),
            label = { ResourceText(id = R.string.sign_up_screen_username_input) },
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = {
                username = it
                usernameError = ""
            }
        )
        TextFieldError(message = usernameError)
        FractionSpacer(fraction = 0.5F)
        // Password input
        org.nextchat.nextchat.ui.widgets.PasswordInput(
            error = passwordError,
            label = R.string.sign_up_screen_password_input,
            value = password,
            onValueChange = { value ->
                password = value
                passwordError = ""
            }
        )
        // Repeat password input
        org.nextchat.nextchat.ui.widgets.PasswordInput(
            error = repeatPasswordError,
            label = R.string.sign_up_screen_repeat_password_input,
            value = repeatPassword,
            onValueChange = { value ->
                repeatPassword = value
                repeatPasswordError = ""
            }
        )
        // Beta key input
        OutlinedTextField(
            isError = betaKeyError.isNotEmpty(),
            label = { ResourceText(id = R.string.sign_up_screen_beta_key_input) },
            modifier = Modifier.fillMaxWidth(),
            value = betaKey,
            onValueChange = {
                betaKey = it
                betaKeyError = ""
            }
        )
        TextFieldError(message = betaKeyError)
        FractionSpacer(fraction = 0.5F)
        // Terms of conditions and privacy policy message
        ResourceText(
            id = R.string.AUTH_PRIVACY_POLICY,
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            modifier = Modifier.padding(start = 2.dp),
        )
        FractionSpacer(fraction = 3F)
        // Actions
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            SignInButton(navController = navController)
            SignUpButton(
                // Inputs
                username = username,
                betaKey = betaKey,
                password = password,
                repeatPassword = repeatPassword,
                // General
                signUpRepository = signUpRepository,
                // Callback
                onErrors = { errors ->
                    // Reset errors
                    usernameError = ""
                    passwordError = ""
                    repeatPasswordError = ""
                    repeatPassword = ""

                    // Set new errors
                    for ((field, message) in errors.iterator()) {
                        when (field) {
                            "username" -> { usernameError = message }
                            "password" -> { passwordError = message }
                            "repeat_password" -> { repeatPasswordError = message }
                            "beta_key" -> { betaKeyError = message }
                            else -> { println("Cannot parse: $field as error (Message: $message)") }
                        }
                    }
                }
            )
        }
    }
}


@Composable
private fun SignInButton(
    navController: NavController
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(0.6F),
        onClick = { navController.navigate(route = Screens.SignIn.route) }
    ) {
        ResourceText(
            id = R.string.sign_up_screen_sign_in_button,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SignUpButton(
    // Inputs
    username: String,
    betaKey: String,
    password: String,
    repeatPassword: String,
    // General
    signUpRepository: SignUpRepository,
    // Callbacks
    onErrors: (Map<String, String>) -> Unit,
) {
    // Hooks
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Content
    Button(onClick = {
        scope.launch {
            signUpRepository.handleSubmit(
                // Fields
                username = username,
                betaKey = betaKey,
                password = password,
                repeatPassword = repeatPassword,
                // General
                context = context,
                // Callbacks
                onErrors = onErrors,
            )
        }
    }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, "")
            FractionSpacer(fraction = 0.5F)
            ResourceText(id = R.string.sign_up_screen_submit_button)
        }
    }
}
