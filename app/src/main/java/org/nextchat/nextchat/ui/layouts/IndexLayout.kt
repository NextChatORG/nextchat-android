package org.nextchat.nextchat.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.nextchat.nextchat.ui.widgets.FractionSpacer
import org.nextchat.nextchat.ui.widgets.NextChatLogo
import org.nextchat.nextchat.ui.widgets.ResourceText

@Composable
fun IndexLayout(
    title: Int,
    titleMargin: Float = 3F,
    content: @Composable () -> Unit
) {
    Scaffold {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            // Logo
            NextChatLogo()
            Spacer(
                modifier = Modifier
                    .width(112.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.primary)
            )
            FractionSpacer()
            // Title
            ResourceText(
                id = title,
                color = Color.Gray,
                fontSize = 8.em,
                fontWeight = FontWeight.W400
            )
            FractionSpacer(fraction = titleMargin)
            // Content
            content()
        }
    }
}
