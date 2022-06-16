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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.playground.domain.model.Country
import com.playground.transaction.compose.R
import com.playground.transaction.compose.ui.components.button.StandardButton
import com.playground.transaction.compose.ui.theme.GrayDark
import com.playground.transaction.compose.ui.theme.GrayMain
import com.playground.transaction.compose.ui.theme.GrayMedium
import com.playground.transaction.compose.ui.theme.PlaygroundTheme
import com.playground.transaction.compose.ui.theme.PrimaryDark
import com.playground.transaction.compose.ui.theme.Spacing
import com.playground.transaction.compose.ui.theme.Typography

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
        Divider(modifier = Modifier.padding(top = Spacing.XXL))
        CountryDetailsRow(country = country)
        PhoneDetailsRow(fullPhoneNumber = fullPhoneNumber)
        Divider()
        TransferExchangeDetailsRow(transferExchangeValue = transferExchangeValue)
        Spacer(modifier = Modifier.weight(1f))
        StandardButton(
            text = stringResource(id = R.string.transaction_summary_transfer),
            modifier = Modifier.fillMaxWidth(),
            onClick = { onTransferAction() }
        )
    }
}

@Composable
private fun TopSection(transferMoney: String, recipientName: String) {
    Text(
        text = stringResource(id = R.string.transaction_summary_title),
        style = Typography.Title,
        modifier = Modifier.padding(bottom = Spacing.XS)
    )
    Text(
        text = transferMoney,
        style = Typography.H5Bold.copy(color = Color.PrimaryDark),
        modifier = Modifier.padding(bottom = Spacing.XXS)
    )
    Text(
        text = stringResource(id = R.string.transaction_summary_title, recipientName.uppercase()),
        style = Typography.SubtitleBold
    )
}

@Composable
private fun CountryDetailsRow(country: Country) {
    Row(Modifier.padding(top = Spacing.MD)) {
        Text(
            text = stringResource(id = R.string.transaction_summary_country),
            style = Typography.Body.copy(color = Color.GrayMedium),
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = country.icon),
            contentDescription = null
        )
        Text(
            text = stringResource(id = country.countryName),
            style = Typography.Body.copy(color = Color.GrayMain),
            modifier = Modifier.padding(start = Spacing.XS)
        )
    }
}

@Composable
private fun PhoneDetailsRow(fullPhoneNumber: String) {
    Row(Modifier.padding(vertical = Spacing.MD)) {
        Text(
            text = stringResource(id = R.string.transaction_summary_phone),
            style = Typography.Body.copy(color = Color.GrayMedium),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = fullPhoneNumber,
            style = Typography.Body.copy(color = Color.GrayMain),
            modifier = Modifier.padding(start = Spacing.XS)
        )
    }
}

@Composable
private fun TransferExchangeDetailsRow(transferExchangeValue: String) {
    Row(Modifier.padding(top = Spacing.MD)) {
        Text(
            text = stringResource(id = R.string.transaction_summary_exchange),
            style = Typography.BodyBold.copy(color = Color.GrayDark),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = transferExchangeValue,
            style = Typography.BodyBold.copy(color = Color.PrimaryDark),
            modifier = Modifier.padding(start = Spacing.XS)
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
