package com.penguinpay.sdk.sendtransaction.data.remote.api

import com.penguinpay.sdk.sendtransaction.data.remote.dto.ExchangeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface TransactionApi {

    @GET("api/latest.json")
    suspend fun getExchange(
        @Query("app_id") appId: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Response<ExchangeDto>
}