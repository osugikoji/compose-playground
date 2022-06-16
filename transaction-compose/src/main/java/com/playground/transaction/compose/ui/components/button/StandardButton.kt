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
import androidx.compose.ui.unit.dp
import com.playground.transaction.compose.ui.theme.PlaygroundColor
import com.playground.transaction.compose.ui.theme.PlaygroundTheme

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
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PlaygroundColor.AccentMain,
            contentColor = Color.White,
            disabledBackgroundColor = PlaygroundColor.GrayExtraLight,
            disabledContentColor = PlaygroundColor.GrayLight
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
