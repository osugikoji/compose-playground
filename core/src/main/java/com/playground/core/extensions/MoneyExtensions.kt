package com.playground.core.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

private const val CURRENCY_SCALE = 2
private const val DIVIDE_VALUE = 100

fun String.toCurrencyFormat(currencyCode: String): String {
    val currency = Currency.getInstance(currencyCode)
    val value = this.currencyFormatToBigDecimal(currencyCode)
    return value.toCurrencyFormat(currency)
}

fun BigDecimal.toCurrencyFormat(currencyCode: String): String {
    val currency = Currency.getInstance(currencyCode)
    return this.toCurrencyFormat(currency)
}

fun String.currencyFormatToBigDecimal(currencyCode: String): BigDecimal {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        currency = Currency.getInstance(currencyCode)
    }
    val currencySymbol = currencyFormat.currency!!.symbol
    val replaceable = String.format(Locale.getDefault(), "[%s,.\\s]", currencySymbol)
    val cleanString = this.replace(replaceable.toRegex(), "")
    return runCatching {
        BigDecimal(cleanString)
            .setScale(CURRENCY_SCALE, BigDecimal.ROUND_FLOOR)
            .divide(BigDecimal(DIVIDE_VALUE), BigDecimal.ROUND_FLOOR)
    }.getOrDefault(BigDecimal.ZERO)
}

private fun BigDecimal.toCurrencyFormat(currency: Currency): String {
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
