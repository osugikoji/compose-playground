package com.playground.transaction.di.provider

import com.playground.transaction.data.remote.api.TransactionApi
import retrofit2.Retrofit

internal object ApiProvider {

    fun transactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)
}