package org.nextchat.nextchat.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldError(message: String) {
    if (message.isNotEmpty()) {
        Text(
            text = message,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 2.dp),
            style = MaterialTheme.typography.caption.copy(color = Color.Red),
        )
    }
}
