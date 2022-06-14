package com.playground.transaction.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.delay

@Composable
internal fun FocusRequester.safeRequestFocus() {
    LaunchedEffect(Unit) {
        delay(100)
        this@safeRequestFocus.requestFocus()
    }
}
