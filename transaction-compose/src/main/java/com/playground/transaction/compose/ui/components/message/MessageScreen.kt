package com.playground.transaction.compose.ui.components.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playground.transaction.compose.ui.components.button.StandardButton
import com.playground.transaction.compose.ui.theme.PlaygroundTheme
import com.playground.transaction.compose.ui.theme.Spacing
import com.playground.transaction.compose.ui.theme.StatusSuccessMain
import com.playground.transaction.compose.ui.theme.Typography

@Composable
internal fun MessageScreen(
    imageVector: ImageVector,
    imageTint: Color? = null,
    modifier: Modifier = Modifier,
    title: String = "",
    message: String = "",
    buttonText: String = "",
    buttonAction: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.BG),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopIcon(imageVector = imageVector, imageTint = imageTint)
            CenterTexts(title = title, message = message)
            BottomButton(buttonText = buttonText, buttonAction = buttonAction)
        }
    }
}

@Composable
internal fun SuccessMessageScreen(
    modifier: Modifier = Modifier,
    title: String = "",
    message: String = "",
    buttonAction: () -> Unit = {},
) {
    MessageScreen(
        imageVector = Icons.Filled.CheckCircle,
        imageTint = Color.StatusSuccessMain,
        modifier = modifier,
        title = title,
        message = message,
        buttonText = "OK",
        buttonAction = buttonAction,
    )
}

@Composable
private fun TopIcon(imageVector: ImageVector, imageTint: Color? = null) {
    Image(
        modifier = Modifier
            .width(112.dp)
            .height(112.dp)
            .padding(bottom = Spacing.XS),
        imageVector = imageVector,
        contentDescription = null,
        colorFilter = imageTint?.let { ColorFilter.tint(it) }
    )
}

@Composable
private fun CenterTexts(title: String = "", message: String = "") {
    if (title.isNotEmpty()) {
        Text(
            modifier = Modifier.padding(vertical = Spacing.XS),
            text = title,
            style = Typography.TitleBold,
            textAlign = TextAlign.Center,
        )
    }
    if (message.isNotEmpty()) {
        Text(
            modifier = Modifier.padding(bottom = Spacing.XS),
            text = message,
            style = Typography.Body,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun BottomButton(buttonText: String = "", buttonAction: () -> Unit = {}) {
    StandardButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Spacing.XS),
        text = buttonText,
        onClick = buttonAction
    )
}

@Preview(showBackground = true)
@Composable
private fun MessageScreenPreview() {
    PlaygroundTheme {
        MessageScreen(
            imageVector = Icons.Filled.Warning,
            imageTint = Color.Red,
            title = "Algo deu errado",
            message = "Houve um problema com a requisição. Tente novamente em alguns instantes.",
            buttonText = "OK"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageScreenSuccessPreview() {
    PlaygroundTheme {
        SuccessMessageScreen(
            title = "Transfer executed successfully",
            message = "The transfer in the amount of R$ 100,00 was successful."
        )
    }
}
