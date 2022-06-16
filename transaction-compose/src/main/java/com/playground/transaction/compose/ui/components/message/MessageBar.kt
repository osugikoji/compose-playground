package com.playground.transaction.compose.ui.components.message

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playground.transaction.compose.ui.components.button.StandardButton
import com.playground.transaction.compose.ui.theme.PlaygroundColor
import kotlinx.coroutines.launch

@Composable
internal fun MessageBar(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SnackbarDefaults.backgroundColor,
) {
    SnackbarHost(snackBarHostState, modifier = modifier) {
        Snackbar(backgroundColor = backgroundColor, modifier = Modifier.padding(16.dp)) {
            Text(it.message)
        }
    }
}

@Composable
internal fun ErrorMessageBar(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    MessageBar(
        snackBarHostState = snackBarHostState,
        modifier = modifier,
        backgroundColor = PlaygroundColor.StatusErrorMain
    )
}

@Preview(showBackground = true)
@Composable
private fun MessageBarPreview() {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        StandardButton(text = "Show snack bar", modifier = Modifier.align(Alignment.Center)) {
            scope.launch { snackBarHostState.showSnackbar(message = "Test message") }
        }
        ErrorMessageBar(
            snackBarHostState = snackBarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
