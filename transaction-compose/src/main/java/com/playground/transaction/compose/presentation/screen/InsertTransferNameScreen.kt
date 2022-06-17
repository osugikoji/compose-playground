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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import com.playground.core.extensions.isFullName
import com.playground.transaction.compose.R
import com.playground.transaction.compose.ui.components.button.StandardButton
import com.playground.transaction.compose.ui.components.textfield.StandardTextField
import com.playground.transaction.compose.ui.theme.GrayMedium
import com.playground.transaction.compose.ui.theme.Spacing
import com.playground.transaction.compose.ui.theme.Typography
import com.playground.transaction.compose.utils.safeRequestFocus

@Composable
internal fun InsertTransferNameScreen(
    transferValue: String,
    nameState: MutableState<String>,
    modifier: Modifier = Modifier,
    onNextAction: () -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    val textFieldContentDescription = stringResource(id = R.string.insert_name_content_desc)
    focusRequester.safeRequestFocus()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(bottom = Spacing.XS),
            text = stringResource(id = R.string.insert_name_title, transferValue),
            style = Typography.Title
        )
        Text(
            modifier = Modifier.padding(bottom = Spacing.XM),
            text = stringResource(id = R.string.insert_name_subtitle),
            style = Typography.Subtitle,
            color = Color.GrayMedium
        )
        StandardTextField(
            value = nameState.value,
            hint = stringResource(id = R.string.insert_name_hint),
            keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Words),
            onTextChanged = { nameState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .semantics { contentDescription = textFieldContentDescription },
        )
        Spacer(modifier = Modifier.weight(1f))
        StandardButton(
            text = stringResource(id = R.string.next_action),
            enabled = nameState.value.isFullName(),
            onClick = { onNextAction() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.BG),
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
