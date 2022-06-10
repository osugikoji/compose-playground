package com.playground.transaction.data.remote.dto

import com.squareup.moshi.Json

internal data class ExchangeDto(
    @Json(name = "base")
    val base: String,
    @Json(name = "rates")
    val rates: Map<String, Double>
)
