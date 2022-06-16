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
import androidx.compose.ui.unit.sp

@Immutable
object Typography {

    @Stable
    val H5: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Color.GrayDark
    )

    @Stable
    val H5Bold: TextStyle = H5.copy(fontWeight = FontWeight.Bold)

    @Stable
    val Title: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color.GrayDark
    )

    @Stable
    val TitleBold: TextStyle = Title.copy(fontWeight = FontWeight.Bold)

    @Stable
    val Subtitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.GrayDark
    )

    @Stable
    val SubtitleBold: TextStyle = Subtitle.copy(fontWeight = FontWeight.Bold)

    @Stable
    val Body: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.GrayDark
    )

    @Stable
    val BodyBold: TextStyle = Subtitle.copy(fontWeight = FontWeight.Bold)
}

@Preview
@Composable
private fun PlaygroundTypographyPreview() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)) {
        Text(
            text = "H5",
            modifier = Modifier.padding(Spacing.XS),
            style = Typography.H5
        )
        Text(
            text = "H5Bold",
            modifier = Modifier.padding(Spacing.XS),
            style = Typography.H5Bold
        )
        Text(
            text = "Title",
            modifier = Modifier.padding(Spacing.XS),
            style = Typography.Title
        )
        Text(
            text = "TitleBold",
            modifier = Modifier.padding(Spacing.XS),
            style = Typography.TitleBold
        )
        Text(
            text = "Subtitle",
            modifier = Modifier.padding(Spacing.XS),
            style = Typography.Subtitle
        )
        Text(
            text = "SubtitleBold",
            modifier = Modifier.padding(Spacing.XS),
            style = Typography.SubtitleBold
        )
        Text(
            text = "Body",
            modifier = Modifier.padding(Spacing.XS),
            style = Typography.Body
        )
        Text(
            text = "BodyBold",
            modifier = Modifier.padding(Spacing.XS),
            style = Typography.BodyBold
        )
    }
}
