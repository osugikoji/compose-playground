package com.playground.data.di.provider

import co.infinum.retromock.Retromock
import com.playground.data.remote.api.TransactionApi
import com.playground.data.remote.api.TransactionMockApi
import retrofit2.Retrofit

internal object ApiProvider {

    fun transactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)

    fun transactionMockApi(retromock: Retromock): TransactionMockApi =
        retromock.create(TransactionMockApi::class.java)
}
