package org.nextchat.nextchat.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FractionSpacer(fraction: Float = 1F) {
    androidx.compose.foundation.layout.Spacer(
        modifier = Modifier.height((fraction * 12).dp)
    )
}
