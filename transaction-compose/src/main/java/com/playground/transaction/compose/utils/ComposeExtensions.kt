package com.playground.transaction.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.delay

private const val SAFE_DELAY = 100L

@Composable
internal fun FocusRequester.safeRequestFocus() {
    LaunchedEffect(Unit) {
        delay(SAFE_DELAY)
        this@safeRequestFocus.requestFocus()
    }
}
