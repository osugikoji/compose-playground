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

@Stable
inline val Color.Companion.PrimaryDark: Color
    get() = Color(color = 0xFF0D47A1)

@Stable
inline val Color.Companion.PrimaryMain: Color
    get() = Color(color = 0xFF2196F3)

@Stable
inline val Color.Companion.PrimaryMedium: Color
    get() = Color(color = 0xFF64B5F6)

@Stable
inline val Color.Companion.AccentMain: Color
    get() = Color(color = 0xFF448AFF)

@Stable
inline val Color.Companion.GrayDark: Color
    get() = Color(color = 0xFF212121)

@Stable
inline val Color.Companion.GrayMain: Color
    get() = Color(color = 0xFF424242)

@Stable
inline val Color.Companion.GrayMedium: Color
    get() = Color(color = 0xFF9E9E9E)

@Stable
inline val Color.Companion.GrayLight: Color
    get() = Color(color = 0xFFE0E0E0)

@Stable
inline val Color.Companion.GrayExtraLight: Color
    get() = Color(color = 0xFFF5F5F5)

@Stable
inline val Color.Companion.StatusErrorMain: Color
    get() = Color(color = 0xFFE63746)

@Stable
inline val Color.Companion.StatusSuccessMain: Color
    get() = Color(color = 0xFF7CD992)

@Preview
@Composable
private fun PlaygroundColorPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "PrimaryDark",
            modifier = Modifier.buildModifier(Color.PrimaryDark)
        )
        Text(
            text = "PrimaryMain",
            modifier = Modifier.buildModifier(Color.PrimaryMain)
        )
        Text(
            text = "PrimaryMedium",
            modifier = Modifier.buildModifier(Color.PrimaryMedium)
        )
        Text(
            text = "AccentMain",
            modifier = Modifier.buildModifier(Color.AccentMain)
        )
        Text(
            text = "GrayDark",
            modifier = Modifier.buildModifier(Color.GrayDark)
        )
        Text(
            text = "GrayMain",
            modifier = Modifier.buildModifier(Color.GrayMain)
        )
        Text(
            text = "GrayMedium",
            modifier = Modifier.buildModifier(Color.GrayMedium)
        )
        Text(
            text = "GrayLight",
            modifier = Modifier.buildModifier(Color.GrayLight)
        )
        Text(
            text = "GrayExtraLight",
            modifier = Modifier.buildModifier(Color.GrayExtraLight)
        )
        Text(
            text = "StatusErrorMain",
            modifier = Modifier.buildModifier(Color.StatusErrorMain)
        )
        Text(
            text = "StatusSuccessMain",
            modifier = Modifier.buildModifier(Color.StatusSuccessMain)
        )
    }
}

private fun Modifier.buildModifier(color: Color): Modifier {
    return this
        .background(color)
        .fillMaxWidth()
        .padding(Spacing.MD)
}
