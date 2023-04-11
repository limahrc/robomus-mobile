package com.nescom.robomus.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun LabelValueColoredText(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    highlightColor: Color,
    baseColor: Color = Color.Black,
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append("$label: ")

            withStyle(style = SpanStyle(color = highlightColor, fontWeight = FontWeight.Bold)) {
                append(value)
            }
        },
        color = baseColor
    )
}