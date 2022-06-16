package com.playground.transaction.compose.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object PlaygroundColor {
    @Stable
    val PrimaryDark = Color(0xFF0D47A1)

    @Stable
    val PrimaryMain = Color(0xFF2196F3)

    @Stable
    val PrimaryMedium = Color(0xFF64B5F6)

    @Stable
    val AccentMain = Color(0xFF448AFF)

    @Stable
    val GrayDark = Color(0xFF212121)

    @Stable
    val GrayMain = Color(0xFF424242)

    @Stable
    val GrayMedium = Color(0xFF9E9E9E)

    @Stable
    val GrayLight = Color(0xFFE0E0E0)

    @Stable
    val GrayExtraLight = Color(0xFFF5F5F5)

    @Stable
    val StatusErrorMain = Color(0xFFE63746)

    @Stable
    val StatusSuccessMain = Color(0xFF7CD992)
}

@Preview(name = "Playground Color Preview")
@Composable
private fun PlaygroundColorPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "PrimaryDark",
            modifier = Modifier.buildModifier(PlaygroundColor.PrimaryDark)
        )
        Text(
            text = "PrimaryMain",
            modifier = Modifier.buildModifier(PlaygroundColor.PrimaryMain)
        )
        Text(
            text = "PrimaryMedium",
            modifier = Modifier.buildModifier(PlaygroundColor.PrimaryMedium)
        )
        Text(
            text = "AccentMain",
            modifier = Modifier.buildModifier(PlaygroundColor.AccentMain)
        )
        Text(
            text = "GrayDark",
            modifier = Modifier.buildModifier(PlaygroundColor.GrayDark)
        )
        Text(
            text = "GrayMain",
            modifier = Modifier.buildModifier(PlaygroundColor.GrayMain)
        )
        Text(
            text = "GrayMedium",
            modifier = Modifier.buildModifier(PlaygroundColor.GrayMedium)
        )
        Text(
            text = "GrayLight",
            modifier = Modifier.buildModifier(PlaygroundColor.GrayLight)
        )
        Text(
            text = "GrayExtraLight",
            modifier = Modifier.buildModifier(PlaygroundColor.GrayExtraLight)
        )
        Text(
            text = "StatusErrorMain",
            modifier = Modifier.buildModifier(PlaygroundColor.StatusErrorMain)
        )
        Text(
            text = "StatusSuccessMain",
            modifier = Modifier.buildModifier(PlaygroundColor.StatusSuccessMain)
        )
    }
}

private fun Modifier.buildModifier(color: Color): Modifier {
    return this
        .background(color)
        .fillMaxWidth()
        .padding(16.dp)
}
