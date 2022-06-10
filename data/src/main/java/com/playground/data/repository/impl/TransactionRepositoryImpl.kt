package com.playground.data.repository.impl

import com.playground.data.remote.api.TransactionApi
import com.playground.data.remote.dto.ExchangeDto
import com.playground.data.remote.dto.SendTransactionDto
import com.playground.data.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Response

internal class TransactionRepositoryImpl(
    private val appId: String,
    private val transactionApi: TransactionApi
) : TransactionRepository {

    override suspend fun getExchange(base: String, currencySymbol: String): Response<ExchangeDto> {
        return withContext(Dispatchers.IO) {
            transactionApi.getExchange(appId, base, currencySymbol)
        }
    }

    override suspend fun sendTransaction(
        sendTransactionDto: SendTransactionDto,
    ): Response<SendTransactionDto> = withContext(Dispatchers.IO) {
        // simulates send transaction request
        delay(FAKE_REQUEST_DELAY)
        Response.success(sendTransactionDto)
    }

    companion object {
        private const val FAKE_REQUEST_DELAY = 2000L
    }
}
