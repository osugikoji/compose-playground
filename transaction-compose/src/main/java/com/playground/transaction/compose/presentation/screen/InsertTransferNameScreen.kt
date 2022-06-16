package com.playground.transaction.compose.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playground.core.extensions.isFullName
import com.playground.transaction.compose.ui.components.button.StandardButton
import com.playground.transaction.compose.ui.components.textfield.StandardTextField
import com.playground.transaction.compose.ui.theme.PlaygroundColor
import com.playground.transaction.compose.ui.theme.PlaygroundTypography
import com.playground.transaction.compose.utils.safeRequestFocus

@Composable
internal fun InsertTransferNameScreen(
    transferValue: String,
    nameState: MutableState<String>,
    modifier: Modifier = Modifier,
    onNextAction: () -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    val title = "Who do you want to send $transferValue?"
    focusRequester.safeRequestFocus()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            style = PlaygroundTypography.Title
        )
        Text(
            modifier = Modifier.padding(bottom = 24.dp),
            text = "Enter the recipient first and last name to proceed.",
            style = PlaygroundTypography.Subtitle,
            color = PlaygroundColor.GrayMedium
        )
        StandardTextField(
            value = nameState.value,
            hint = "Complete name",
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words
            ),
            onTextChanged = { nameState.value = it }
        )
        Spacer(modifier = Modifier.weight(1f))
        StandardButton(
            text = "Next",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            enabled = nameState.value.isFullName(),
            onClick = { onNextAction() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InsertTransferNamePreview() {
    val name = rememberSaveable { mutableStateOf("") }
    InsertTransferNameScreen(
        transferValue = "R$ 100,00",
        nameState = name,
    )
}
