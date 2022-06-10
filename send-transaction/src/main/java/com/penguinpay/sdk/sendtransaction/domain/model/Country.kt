package com.penguinpay.sdk.sendtransaction.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.penguinpay.sdk.sendtransaction.R

internal enum class Country(
    @DrawableRes val icon: Int,
    @StringRes val countryName: Int,
    val phonePrefix: String,
    val phoneNumberLength: Int,
    val currencySymbol: String
) {
    BRAZIL(R.drawable.ic_brazil_flag, R.string.country_brazil, "+55", 9, CurrencySymbol.BRAZIL),
    KENYA(R.drawable.ic_kenya_flag, R.string.country_kenya, "+254", 9, CurrencySymbol.KENYA),
    NIGERIA(R.drawable.ic_nigeria_flag, R.string.country_nigeria, "+234", 7, CurrencySymbol.NIGERIA),
    TANZANIA(R.drawable.ic_tanzania_flag, R.string.country_tanzania, "+255", 9, CurrencySymbol.TANZANIA),
    UGANDA(R.drawable.ic_uganda_flag, R.string.country_uganda, "+256", 7, CurrencySymbol.UGANDA),
    NONE(-1, -1, "", -1, "")
}
