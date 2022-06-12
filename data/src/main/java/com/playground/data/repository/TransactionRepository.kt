package com.playground.data.repository

import com.playground.data.executor.SourceResult
import com.playground.data.remote.dto.ExchangeDto
import com.playground.data.remote.dto.SendTransactionDto

interface TransactionRepository {

    suspend fun getExchange(base: String, currencyCode: String): SourceResult<ExchangeDto>

    suspend fun sendTransaction(sendTransactionDto: SendTransactionDto): SourceResult<Unit>
}
