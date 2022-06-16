package com.playground.transaction.compose.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playground.core.extensions.formatToDigits
import com.playground.core.extensions.formatToPhone
import com.playground.domain.model.Country
import com.playground.transaction.compose.ui.components.button.StandardButton
import com.playground.transaction.compose.ui.components.textfield.StandardTextField
import com.playground.transaction.compose.ui.theme.PlaygroundColor
import com.playground.transaction.compose.ui.theme.PlaygroundTypography
import com.playground.transaction.compose.utils.safeRequestFocus

@Composable
internal fun InsertRecipientPhoneScreen(
    phoneState: MutableState<String>,
    phoneCountry: Country,
    modifier: Modifier = Modifier,
    onNextAction: () -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    focusRequester.safeRequestFocus()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        TitleSection()
        TextFieldSection(phoneState = phoneState, phoneCountry = phoneCountry)
        Spacer(modifier = Modifier.weight(1f))
        ButtonSection(
            phoneState = phoneState,
            phoneCountry = phoneCountry,
            onNextAction = onNextAction
        )
    }
}

@Composable
private fun TitleSection() {
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = "What is the recipient phone?",
        style = PlaygroundTypography.Title
    )
    Text(
        modifier = Modifier.padding(bottom = 24.dp),
        text = "Enter the recipient phone number",
        style = PlaygroundTypography.Subtitle,
        color = PlaygroundColor.GrayMedium
    )
}

@Composable
private fun TextFieldSection(
    phoneState: MutableState<String>,
    phoneCountry: Country,
) {
    val focusRequester = remember { FocusRequester() }
    focusRequester.safeRequestFocus()
    Row {
        StandardTextField(
            modifier = Modifier.weight(1f),
            hint = "Prefix",
            value = phoneCountry.phonePrefix,
            readOnly = true
        )
        StandardTextField(
            value = phoneState.value,
            selectAt = phoneState.value.length,
            hint = "Phone number",
            modifier = Modifier
                .focusRequester(focusRequester)
                .weight(2f)
                .padding(start = 24.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onTextChanged = { phoneState.value = it.formatToPhone(phoneCountry.countryIso) }
        )
    }
}

@Composable
private fun ButtonSection(
    phoneState: MutableState<String>,
    phoneCountry: Country,
    onNextAction: () -> Unit = {},
) {
    val enableButton = phoneState.value.formatToDigits().length >= phoneCountry.phoneNumberLength
    StandardButton(
        text = "Next",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        enabled = enableButton,
        onClick = { onNextAction() }
    )
}

@Preview(showBackground = true)
@Composable
private fun InsertRecipientPhoneScreenPreview() {
    val value = rememberSaveable { mutableStateOf("") }
    InsertRecipientPhoneScreen(
        phoneState = value,
        phoneCountry = Country.BRAZIL
    )
}
