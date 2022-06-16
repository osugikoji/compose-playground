package com.playground.transaction.compose.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.playground.transaction.compose.ui.theme.GrayMedium
import com.playground.transaction.compose.ui.theme.Spacing
import com.playground.transaction.compose.ui.theme.Typography

@Composable
internal fun SelectCountryScreen(
    modifier: Modifier = Modifier,
    onSelectedCountry: (Country) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(bottom = Spacing.XS),
            text = stringResource(id = R.string.select_country_title),
            style = Typography.Title
        )
        Text(
            modifier = Modifier.padding(bottom = Spacing.XM),
            text = stringResource(id = R.string.select_country_subtitle),
            style = Typography.Subtitle,
            color = Color.GrayMedium
        )
        LazyColumn {
            items(Country.getAllCountries()) { country ->
                CountryItem(country, onSelectedCountry)
                Divider()
            }
        }
    }
}

@Composable
private fun CountryItem(country: Country, onClickItem: (Country) -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClickItem(country) }
            .fillMaxWidth()
            .padding(Spacing.MD)
    ) {
        Image(painter = painterResource(id = country.icon), contentDescription = null)
        Text(
            text = stringResource(id = country.countryName),
            modifier = Modifier.padding(start = Spacing.MD)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectCountryScreenPreview() {
    SelectCountryScreen()
}
