package com.playground.transaction.compose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette =
    lightColors(
        primary = Color.PrimaryMain,
        primaryVariant = Color.PrimaryMedium
    )

@Composable
internal fun PlaygroundTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = LightColorPalette, content = content)
}
