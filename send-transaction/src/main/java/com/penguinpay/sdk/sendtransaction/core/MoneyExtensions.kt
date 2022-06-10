package com.penguinpay.sdk.sendtransaction.core

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

private val defaultLocale = Locale.US
private const val CURRENCY_SCALE = 2

internal fun BigDecimal.toCurrencySymbol(
    locale: Locale = defaultLocale
): String {
    val currency = NumberFormat.getCurrencyInstance(locale).currency
    return this.toCurrencySymbol(requireNotNull(currency))
}

internal fun BigDecimal.toCurrencySymbol(currencySymbol: String): String {
    val currency = Currency.getInstance(currencySymbol)
    return this.toCurrencySymbol(currency)
}

internal fun String.currencySymbolToBigDecimal(
    locale: Locale = defaultLocale
): BigDecimal {
    val currencyFormat = NumberFormat.getCurrencyInstance(locale).currency!!.symbol
    val replaceable = String.format("[%s,.\\s]", currencyFormat)
    val cleanString = this.replace(replaceable.toRegex(), "")
    return runCatching {
        BigDecimal(cleanString)
            .setScale(CURRENCY_SCALE, BigDecimal.ROUND_FLOOR)
            .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
    }.getOrDefault(BigDecimal.ZERO)
}

private fun BigDecimal.toCurrencySymbol(currency: Currency): String {

    val numberFormatted = NumberFormat.getNumberInstance().apply {

        setCurrency(currency)
        minimumFractionDigits = CURRENCY_SCALE
        maximumFractionDigits = CURRENCY_SCALE
    }
    val currencySymbol = numberFormatted.currency!!.symbol

    if (BigDecimal.ZERO.setScale(CURRENCY_SCALE) == this) {
        val monetaryValue = this.toString().replace(".", ",")
        return "$currencySymbol $monetaryValue"
    }
    val monetaryValue = numberFormatted.format(this)
    return "$currencySymbol $monetaryValue"
}
