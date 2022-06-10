package com.playground.data.remote.dto

import com.squareup.moshi.Json

data class ExchangeDto(
    @Json(name = "base")
    val base: String,
    @Json(name = "rates")
    val rates: Map<String, Double>,
)
