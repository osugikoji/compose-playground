package com.penguinpay.sdk.sendtransaction.domain.model

import com.penguinpay.sdk.sendtransaction.util.formatToDigits

internal data class SendTransactionDataHolder(
    var transferValue: String = "",
    var recipientName: String = "",
    var selectedCountry: Country = Country.NONE,
    var phoneNumber: String = "",
    var exchangedValue: String = ""
) {
    fun isValidPhoneNumber(): Boolean =
        phoneNumber.formatToDigits().length == selectedCountry.phoneNumberLength

    fun getFullPhoneNumber(): String {
        val prefix = selectedCountry.phonePrefix
        return "$prefix $phoneNumber"
    }
}