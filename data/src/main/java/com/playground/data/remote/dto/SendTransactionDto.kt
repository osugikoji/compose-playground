package com.playground.data.remote.dto

import com.squareup.moshi.Json

data class SendTransactionDto(
    @Json(name = "base_value")
    val baseValue: String,
    @Json(name = "value")
    val value: String,
    @Json(name = "recipient_currency")
    val recipientCurrency: String,
    @Json(name = "phone_number")
    val phoneNumber: String,
)
