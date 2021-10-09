package org.nextchat.nextchat.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun Spacer(modifier: Modifier){
    Spacer(modifier = modifier)
}

@Composable
fun Text(
    stringResource: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit =  TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    style: TextStyle = LocalTextStyle.current ){
    Text(text = stringResource,
        color = color,
        fontSize = fontSize,
        textAlign = textAlign,
        modifier = modifier,
        style = style)
}

val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { TextStyle.Default }
