package com.playground.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.playground.domain.R

enum class Country(
    @DrawableRes val icon: Int,
    @StringRes val countryName: Int,
    val phonePrefix: String,
    val phoneNumberLength: Int,
    val currencySymbol: String,
    val countryIso: String,
) {
    BRAZIL(
        icon = R.drawable.ic_brazil_flag,
        countryName = R.string.country_brazil,
        phonePrefix = "+55",
        phoneNumberLength = 9,
        currencySymbol = CurrencyCode.BRAZIL,
        countryIso = "BR",
    ),
    KENYA(
        icon = R.drawable.ic_kenya_flag,
        countryName = R.string.country_kenya,
        phonePrefix = "+254",
        phoneNumberLength = 9,
        currencySymbol = CurrencyCode.KENYA,
        countryIso = "KE",
    ),
    NIGERIA(
        icon = R.drawable.ic_nigeria_flag,
        countryName = R.string.country_nigeria,
        phonePrefix = "+234",
        phoneNumberLength = 7,
        currencySymbol = CurrencyCode.NIGERIA,
        countryIso = "NG",
    ),
    TANZANIA(
        icon = R.drawable.ic_tanzania_flag,
        countryName = R.string.country_tanzania,
        phonePrefix = "+255",
        phoneNumberLength = 9,
        currencySymbol = CurrencyCode.TANZANIA,
        countryIso = "TZ",
    ),
    UGANDA(
        icon = R.drawable.ic_uganda_flag,
        countryName = R.string.country_uganda,
        phonePrefix = "+256",
        phoneNumberLength = 7,
        currencySymbol = CurrencyCode.UGANDA,
        countryIso = "UG",
    ),
    NONE(
        icon = -1,
        countryName = -1,
        phonePrefix = "",
        phoneNumberLength = -1,
        currencySymbol = "",
        countryIso = "",
    );

    companion object {
        fun getAllCountries() = values().filter { it != NONE }
    }
}
