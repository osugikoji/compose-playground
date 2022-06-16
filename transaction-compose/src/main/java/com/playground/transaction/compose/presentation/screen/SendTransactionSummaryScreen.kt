package com.playground.transaction.compose.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playground.domain.model.Country
import com.playground.transaction.compose.ui.components.button.StandardButton
import com.playground.transaction.compose.ui.theme.PlaygroundColor
import com.playground.transaction.compose.ui.theme.PlaygroundTheme
import com.playground.transaction.compose.ui.theme.PlaygroundTypography

@Composable
internal fun SendTransactionSummaryScreen(
    transferMoney: String,
    recipientName: String,
    country: Country,
    fullPhoneNumber: String,
    transferExchangeValue: String,
    modifier: Modifier = Modifier,
    onTransferAction: () -> Unit = {},
) {
    Column(modifier = modifier) {
        TopSection(transferMoney = transferMoney, recipientName = recipientName)
        Divider(modifier = Modifier.padding(top = 56.dp))
        CountryDetailsRow(country = country)
        PhoneDetailsRow(fullPhoneNumber = fullPhoneNumber)
        Divider()
        TransferExchangeDetailsRow(transferExchangeValue = transferExchangeValue)
        Spacer(modifier = Modifier.weight(1f))
        StandardButton(
            text = "Transfer",
            modifier = Modifier.fillMaxWidth(),
            onClick = { onTransferAction() }
        )
    }
}

@Composable
private fun TopSection(transferMoney: String, recipientName: String) {
    Text(
        text = "Transferring",
        style = PlaygroundTypography.Title,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Text(
        text = transferMoney,
        style = PlaygroundTypography.H5Bold.copy(color = PlaygroundColor.PrimaryDark),
        modifier = Modifier.padding(bottom = 4.dp)
    )
    Text(
        text = "to ${recipientName.uppercase()}",
        style = PlaygroundTypography.SubtitleBold
    )
}

@Composable
private fun CountryDetailsRow(country: Country) {
    Row(Modifier.padding(top = 16.dp)) {
        Text(
            text = "Country",
            style = PlaygroundTypography.Body.copy(color = PlaygroundColor.GrayMedium),
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = country.icon),
            contentDescription = null
        )
        Text(
            text = stringResource(id = country.countryName),
            style = PlaygroundTypography.Body.copy(color = PlaygroundColor.GrayMain),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun PhoneDetailsRow(fullPhoneNumber: String) {
    Row(Modifier.padding(vertical = 16.dp)) {
        Text(
            text = "Phone number",
            style = PlaygroundTypography.Body.copy(color = PlaygroundColor.GrayMedium),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = fullPhoneNumber,
            style = PlaygroundTypography.Body.copy(color = PlaygroundColor.GrayMain),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun TransferExchangeDetailsRow(transferExchangeValue: String) {
    Row(Modifier.padding(top = 16.dp)) {
        Text(
            text = "Transfer exchange",
            style = PlaygroundTypography.BodyBold.copy(color = PlaygroundColor.GrayDark),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = transferExchangeValue,
            style = PlaygroundTypography.BodyBold.copy(color = PlaygroundColor.PrimaryDark),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SendTransactionSummaryPreview() {
    PlaygroundTheme {
        SendTransactionSummaryScreen(
            transferMoney = "$ 100,00",
            recipientName = "Koji",
            country = Country.BRAZIL,
            fullPhoneNumber = "+254 98652-9874",
            transferExchangeValue = "R$ 500,00"
        )
    }
}
