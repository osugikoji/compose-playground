package com.playground.transaction.compose.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
object PlaygroundTypography {

    @Stable
    val H5: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = PlaygroundColor.GrayDark
    )

    @Stable
    val H5Bold: TextStyle = H5.copy(fontWeight = FontWeight.Bold)

    @Stable
    val Title: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = PlaygroundColor.GrayDark
    )

    @Stable
    val TitleBold: TextStyle = Title.copy(fontWeight = FontWeight.Bold)

    @Stable
    val Subtitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = PlaygroundColor.GrayDark
    )

    @Stable
    val SubtitleBold: TextStyle = Subtitle.copy(fontWeight = FontWeight.Bold)

    @Stable
    val Body: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = PlaygroundColor.GrayDark
    )

    @Stable
    val BodyBold: TextStyle = Subtitle.copy(fontWeight = FontWeight.Bold)
}

@Preview(name = "Playground Typography Preview")
@Composable
private fun PlaygroundTypographyPreview() {
    val padding = 8.dp
    Column(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        Text(
            text = "H5",
            modifier = Modifier.padding(padding),
            style = PlaygroundTypography.H5
        )
        Text(
            text = "H5Bold",
            modifier = Modifier.padding(padding),
            style = PlaygroundTypography.H5Bold
        )
        Text(
            text = "Title",
            modifier = Modifier.padding(padding),
            style = PlaygroundTypography.Title
        )
        Text(
            text = "TitleBold",
            modifier = Modifier.padding(padding),
            style = PlaygroundTypography.TitleBold
        )
        Text(
            text = "Subtitle",
            modifier = Modifier.padding(padding),
            style = PlaygroundTypography.Subtitle
        )
        Text(
            text = "SubtitleBold",
            modifier = Modifier.padding(padding),
            style = PlaygroundTypography.SubtitleBold
        )
        Text(
            text = "Body",
            modifier = Modifier.padding(padding),
            style = PlaygroundTypography.Body
        )
        Text(
            text = "BodyBold",
            modifier = Modifier.padding(padding),
            style = PlaygroundTypography.BodyBold
        )
    }
}
