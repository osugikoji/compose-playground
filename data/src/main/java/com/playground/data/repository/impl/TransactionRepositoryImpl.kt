package com.playground.data.repository.impl

import com.playground.data.executor.SourceExecutor
import com.playground.data.executor.SourceResult
import com.playground.data.remote.api.TransactionApi
import com.playground.data.remote.api.TransactionMockApi
import com.playground.data.remote.dto.ExchangeDto
import com.playground.data.remote.dto.SendTransactionDto
import com.playground.data.repository.TransactionRepository

internal class TransactionRepositoryImpl(
    private val appId: String,
    private val sourceExecutor: SourceExecutor,
    private val transactionApi: TransactionApi,
    private val transactionMockApi: TransactionMockApi,
) : TransactionRepository {

    override suspend fun getExchange(
        base: String,
        currencyCode: String,
    ): SourceResult<ExchangeDto> {
        return sourceExecutor.execute {
            transactionApi.getExchange(appId, base, currencyCode)
        }
    }

    override suspend fun sendTransaction(
        sendTransactionDto: SendTransactionDto,
    ): SourceResult<Unit> {
        return sourceExecutor.execute { transactionMockApi.sendTransaction(sendTransactionDto) }
    }
}
