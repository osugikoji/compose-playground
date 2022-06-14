package com.playground.transaction.compose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette =
    lightColors(
        primary = PlaygroundColor.PrimaryMain,
        primaryVariant = PlaygroundColor.PrimaryMedium
    )

@Composable
internal fun PlaygroundTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = LightColorPalette, content = content)
}
