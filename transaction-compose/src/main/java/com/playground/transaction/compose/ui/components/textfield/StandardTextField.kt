package com.playground.transaction.compose.ui.components.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playground.transaction.compose.ui.theme.PlaygroundColor

@Composable
internal fun StandardTextField(
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    selectAt: Int? = null,
    onTextChanged: (String) -> Unit = {},
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
    var textFieldValue = textFieldValueState.copy(text = value)
    selectAt?.let { textFieldValue = TextFieldValue(value, TextRange(it)) }
    TextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValueState = it
            onTextChanged(it.text)
        },
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = hint) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = PlaygroundColor.GrayExtraLight
        )
    )
}

@Preview(name = "Standard Text Field Preview")
@Composable
private fun StandardTextFieldPreview() {
    StandardTextField(value = "", hint = "Preview")
}
