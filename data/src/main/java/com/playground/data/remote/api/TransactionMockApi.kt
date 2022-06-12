package com.playground.data.remote.api

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockBehavior
import co.infinum.retromock.meta.MockResponse
import com.playground.data.remote.dto.SendTransactionDto
import retrofit2.Response
import retrofit2.http.POST

internal interface TransactionMockApi {

    @Mock
    @MockBehavior(durationMillis = 2000, durationDeviation = 500)
    @MockResponse(code = 200)
    @POST("api/send-transaction")
    suspend fun sendTransaction(
        sendTransactionDto: SendTransactionDto,
    ): Response<Unit>
}
