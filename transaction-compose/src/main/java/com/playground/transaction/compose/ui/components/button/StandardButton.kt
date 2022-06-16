package com.playground.transaction.compose.ui.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.playground.transaction.compose.ui.theme.AccentMain
import com.playground.transaction.compose.ui.theme.GrayExtraLight
import com.playground.transaction.compose.ui.theme.GrayLight
import com.playground.transaction.compose.ui.theme.PlaygroundTheme
import com.playground.transaction.compose.ui.theme.Spacing

@Composable
internal fun StandardButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(Spacing.XS),
        contentPadding = PaddingValues(Spacing.MD),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.AccentMain,
            contentColor = Color.White,
            disabledBackgroundColor = Color.GrayExtraLight,
            disabledContentColor = Color.GrayLight
        ),
        onClick = onClick) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun StandardButtonPreview() {
    PlaygroundTheme {
        StandardButton(
            text = "Preview",
            enabled = true
        )
    }
}
