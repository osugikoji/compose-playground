package com.playground.transaction.presentation.sendtransaction.model

import com.playground.core.extensions.currencyFormatToBigDecimal
import com.playground.core.extensions.formatToDigits
import com.playground.core.extensions.toCurrencyFormat
import com.playground.domain.model.Country
import com.playground.domain.model.CurrencyCode
import java.math.BigDecimal

internal data class SendTransactionDataHolder(
    var transferValue: BigDecimal = BigDecimal.ZERO,
    var recipientName: String = "",
    var selectedCountry: Country = Country.NONE,
    var phoneNumber: String = ""
) {

    fun setTransferValue(value: String) {
        this.transferValue = value.currencyFormatToBigDecimal(CurrencyCode.UNITED_STATES)
    }

    fun getTransferValueAsCurrency(): String {
        return transferValue.toCurrencyFormat(CurrencyCode.UNITED_STATES)
    }

    fun isValidPhoneNumber(): Boolean =
        phoneNumber.formatToDigits().length == selectedCountry.phoneNumberLength

    fun getFullPhoneNumber(): String {
        val prefix = selectedCountry.phonePrefix
        return "$prefix $phoneNumber"
    }

    fun isTransferValueNotEmpty(): Boolean {
        return transferValue > BigDecimal.ZERO.setScale(2)
    }
}
