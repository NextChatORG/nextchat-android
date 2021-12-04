package org.nextchat.nextchat.ui.common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import org.nextchat.nextchat.R

@Composable
fun NextChatLogo(fontSize: TextUnit = MaterialTheme.typography.h3.fontSize) {
    ResourceText(
        id = R.string.app_name,
        color = MaterialTheme.colors.primary,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}
