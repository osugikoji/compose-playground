package com.playground.transaction.compose.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.playground.transaction.compose.ui.theme.PlaygroundTheme

@Composable
internal fun LoadingScreen() {
    val backgroundColor = Color(0xFFB3000000)
    Box(Modifier
        .background(backgroundColor)
        .fillMaxWidth()
        .fillMaxHeight()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            enabled = true,
            onClick = {}),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    PlaygroundTheme {
        LoadingScreen()
    }
}
