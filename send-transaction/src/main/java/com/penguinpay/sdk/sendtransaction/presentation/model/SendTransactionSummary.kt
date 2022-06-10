package com.penguinpay.sdk.sendtransaction.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal data class SendTransactionSummary(
    val transferValue: String,
    val recipientName: String,
    @StringRes val countryName: Int,
    @DrawableRes val countryIcon: Int,
    val fullPhoneNumber: String,
    val exchangedValue: String
)
