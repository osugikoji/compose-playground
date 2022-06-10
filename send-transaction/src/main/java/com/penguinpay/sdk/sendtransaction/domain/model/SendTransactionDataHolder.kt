package com.penguinpay.sdk.sendtransaction.domain.model

import com.penguinpay.sdk.sendtransaction.core.currencySymbolToBigDecimal
import com.penguinpay.sdk.sendtransaction.core.toCurrencySymbol
import com.penguinpay.sdk.sendtransaction.util.formatToDigits
import java.math.BigDecimal

internal data class SendTransactionDataHolder(
    var transferValue: BigDecimal = BigDecimal.ZERO,
    var recipientName: String = "",
    var selectedCountry: Country = Country.NONE,
    var phoneNumber: String = ""
) {

    fun setTransferValue(value: String) {
        this.transferValue = value.currencySymbolToBigDecimal()
    }

    fun getTransferValueAsCurrency(): String {
        return transferValue.toCurrencySymbol()
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