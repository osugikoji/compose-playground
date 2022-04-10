package com.penguinpay.sdk.sendtransaction.di.provider

import com.penguinpay.sdk.sendtransaction.data.remote.api.TransactionApi
import retrofit2.Retrofit

internal object ApiProvider {

    fun transactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)
}