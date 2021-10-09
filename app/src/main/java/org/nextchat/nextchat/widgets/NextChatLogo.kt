package org.nextchat.nextchat.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.res.stringResource
import org.nextchat.nextchat.R

@Composable
fun NextChatLogo(fontSize: TextUnit = MaterialTheme.typography.h3.fontSize) {
    Text(
        text = stringResource(id = R.string.app_name),
        color = MaterialTheme.colors.primary,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}
