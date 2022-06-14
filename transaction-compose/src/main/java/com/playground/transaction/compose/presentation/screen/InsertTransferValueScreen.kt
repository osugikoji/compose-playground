package com.playground.transaction.compose.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playground.transaction.compose.ui.components.StandardTextField
import com.playground.transaction.compose.ui.theme.PlaygroundColor
import com.playground.transaction.compose.ui.theme.PlaygroundTypography
import com.playground.transaction.compose.utils.safeRequestFocus

@Composable
internal fun InsertTransferValueScreen(
    valueInput: State<String>,
    modifier: Modifier = Modifier,
    onValueInputChanged: (String) -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    focusRequester.safeRequestFocus()
    Column(
        modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "What is the transfer value",
            style = PlaygroundTypography.Title
        )
        Text(
            modifier = Modifier.padding(bottom = 24.dp),
            text = "Enter a real value that you want to send.",
            style = PlaygroundTypography.Subtitle,
            color = PlaygroundColor.GrayMedium
        )
        StandardTextField(
            value = valueInput.value,
            selectAt = valueInput.value.length,
            hint = "Value",
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
            ,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            onTextChanged = onValueInputChanged
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InsertTransferValuePreview() {
    val value = rememberSaveable { mutableStateOf("") }
    InsertTransferValueScreen(value)
}
