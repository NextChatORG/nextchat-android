package org.nextchat.nextchat.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun NextChatLogo(fontSize: TextUnit = MaterialTheme.typography.h3.fontSize) {
    Text(
        "NextChat",
        color = MaterialTheme.colors.primary,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}
