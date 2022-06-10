package com.playground.data.repository

import com.playground.data.remote.dto.ExchangeDto
import com.playground.data.remote.dto.SendTransactionDto
import retrofit2.Response

interface TransactionRepository {

    suspend fun getExchange(base: String, currencySymbol: String): Response<ExchangeDto>

    suspend fun sendTransaction(sendTransactionDto: SendTransactionDto): Response<SendTransactionDto>
}
