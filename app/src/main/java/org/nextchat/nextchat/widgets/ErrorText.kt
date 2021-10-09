package org.nextchat.nextchat.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorText(message: String) {
    if (message.isNotEmpty())
        Text(
            text = message,
            style = MaterialTheme.typography.caption.copy(color = Color.Red),
        )
}
