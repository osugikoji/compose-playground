package com.penguinpay.sdk.sendtransaction.data.repository

import com.penguinpay.sdk.sendtransaction.BuildConfig
import com.penguinpay.sdk.sendtransaction.data.remote.api.TransactionApi
import com.penguinpay.sdk.sendtransaction.data.remote.dto.ExchangeDto
import com.penguinpay.sdk.sendtransaction.data.remote.dto.SendTransactionDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Response

internal class TransactionRepository(private val transactionApi: TransactionApi) {

    suspend fun getExchange(base: String, currencySymbol: String): Response<ExchangeDto> {
        return withContext(Dispatchers.IO) {
            transactionApi.getExchange(BuildConfig.APP_ID, base, currencySymbol)
        }
    }

    suspend fun sendTransaction(
        sendTransactionDto: SendTransactionDto
    ): Response<SendTransactionDto> = withContext(Dispatchers.IO) {
        // simulates send transaction request
        delay(FAKE_REQUEST_DELAY)
        Response.success(sendTransactionDto)
    }

    companion object {
        private const val FAKE_REQUEST_DELAY = 2000L
    }
}