package com.playground.transaction.compose.ui.components.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.playground.transaction.compose.ui.theme.GrayLight
import com.playground.transaction.compose.ui.theme.PrimaryDark
import com.playground.transaction.compose.ui.theme.Typography

@Composable
internal fun StandardAppBar(
    title: String,
    modifier: Modifier = Modifier,
    startIcon: ImageVector? = null,
    startIconDescription: String? = null,
    endIcon: ImageVector? = null,
    endIconDescription: String? = null,
    onStartIconAction: () -> Unit = {},
    onEndIconAction: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = Typography.Title,
                color = Color.GrayLight
            )
        },
        backgroundColor = Color.PrimaryDark,
        navigationIcon = {
            startIcon?.let {
                AppBarIcon(
                    icon = it,
                    description = startIconDescription,
                    onClick = onStartIconAction,
                )
            }
        },
        actions = {
            endIcon?.let {
                AppBarIcon(
                    icon = it,
                    description = endIconDescription,
                    onClick = onEndIconAction,
                )
            }
        }
    )
}

@Composable
private fun AppBarIcon(
    icon: ImageVector,
    description: String? = null,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.GrayLight
        )
    }
}

@Preview(name = "Standard App Bar Preview")
@Composable
private fun StandardAppBarPreview() {
    StandardAppBar(
        title = "Title",
        startIcon = Icons.Filled.ArrowBack,
        endIcon = Icons.Filled.Close,
        onStartIconAction = {},
        onEndIconAction = {}
    )
}
