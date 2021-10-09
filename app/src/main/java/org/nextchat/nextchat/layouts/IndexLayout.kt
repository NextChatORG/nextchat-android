package org.nextchat.nextchat.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.nextchat.nextchat.widgets.NextChatLogo

@Composable
fun IndexLayout(
    title: String,
    titleMargin: Dp = 36.dp,
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
            Spacer(modifier = Modifier.height(12.dp))
            // Title
            Text(
                text = title,
                color = Color.Gray,
                fontSize = 8.em,
                fontWeight = FontWeight.W400
            )
            Spacer(modifier = Modifier.height(titleMargin))
            // Content
            content()
        }
    }
}
