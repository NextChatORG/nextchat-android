package org.nextchat.nextchat.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordInput(
    error: String,
    label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    space: Float = 0.5F
) {
    // State
    var showingPassword by remember { mutableStateOf(false) }

    // Content
    OutlinedTextField(
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onBackground
        ),
        isError = error.isNotEmpty(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { ResourceText(id = label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
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
    FractionSpacer(fraction = space)
}
