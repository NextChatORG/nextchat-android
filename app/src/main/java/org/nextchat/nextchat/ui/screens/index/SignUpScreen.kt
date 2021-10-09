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
import org.nextchat.nextchat.ui.layouts.IndexLayout
import org.nextchat.nextchat.R
import org.nextchat.nextchat.constants.AccountStorage
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.widgets.FractionSpacer
import org.nextchat.nextchat.ui.widgets.ResourceText
import org.nextchat.nextchat.ui.widgets.TextFieldError

@Composable
fun SignUpScreen(
    accountStorage: AccountStorage,
    navController: NavController
) {
    // States
    var username by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    var repeatPassword by remember { mutableStateOf("") }
    var repeatPasswordError by remember { mutableStateOf("") }

    // Content
    IndexLayout(title = R.string.sign_up_title) {
        // Username input
        OutlinedTextField(
            isError = usernameError.isNotEmpty(),
            label = { Text("Username") },
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
        PasswordInput(
            error = passwordError,
            value = password,
            onValueChange = { value ->
                password = value
                passwordError = ""
            }
        )
        // Repeat password input
        PasswordInput(
            error = repeatPasswordError,
            value = repeatPassword,
            onValueChange = { value ->
                repeatPassword = value
                repeatPasswordError = ""
            }
        )
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
                password = password,
                repeatPassword = repeatPassword,
                // General
                accountStorage = accountStorage,
                navController = navController,
            )
        }
    }
}

@Composable
private fun PasswordInput(
    error: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    // State
    var showingPassword by remember { mutableStateOf(false) }

    // Content
    OutlinedTextField(
        isError = error.isNotEmpty(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { Text("Password") },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { showingPassword = !showingPassword }) {
                Icon(
                    imageVector = if (showingPassword) { Icons.Filled.VisibilityOff } else { Icons.Filled.Visibility },
                    contentDescription = if (showingPassword) { "Hide password" } else { "Show password" }
                )
            }
        },
        value = value,
        visualTransformation = if (showingPassword) { VisualTransformation.None } else { PasswordVisualTransformation() },
        onValueChange = onValueChange
    )
    TextFieldError(message = error)
    FractionSpacer(fraction = 0.5F)
}

@Composable
private fun SignInButton(
    navController: NavController
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(0.6F),
        onClick = { navController.navigate(route = Screens.SignIn.route) }
    ) {
        Text(
            text = "I have an account",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SignUpButton(
    // Inputs
    username: String,
    password: String,
    repeatPassword: String,
    // General
    accountStorage: AccountStorage,
    navController: NavController,
) {
    // Hooks
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Content
    Button(onClick = {}) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, "")
            FractionSpacer(fraction = 0.5F)
            Text(text = "Sign up")
        }
    }
}
