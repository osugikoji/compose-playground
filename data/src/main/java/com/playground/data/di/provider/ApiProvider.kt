package com.playground.data.di.provider

import com.playground.data.remote.api.TransactionApi
import retrofit2.Retrofit

internal object ApiProvider {

    fun transactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)
}
