package org.nextchat.nextchat.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(
    error: String,
    label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    space: Float = 0.5F
) {
    OutlinedTextField(
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onBackground
        ),
        isError = error.isNotEmpty(),
        label = { ResourceText(id = label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        value = value,
        onValueChange = onValueChange
    )
    TextFieldError(message = error)
    FractionSpacer(fraction = space)
}
