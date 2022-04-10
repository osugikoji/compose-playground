package com.penguinpay.sdk.sendtransaction.data.remote.dto

import com.squareup.moshi.Json

internal data class ExchangeDto(
    @Json(name = "base")
    val base: String,
    @Json(name = "rates")
    val rates: Map<String, Double>
)
