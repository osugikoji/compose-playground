package com.playground.data.di.module

import com.playground.data.di.provider.ApiProvider
import com.playground.data.repository.TransactionRepository
import com.playground.data.repository.impl.TransactionRepositoryImpl
import org.koin.dsl.module

internal class DataSourceModule(private val appId: String) {

    fun provide() = provideRepositories().plus(provideApis())

    private fun provideRepositories() = module {
        factory<TransactionRepository> { TransactionRepositoryImpl(appId, get()) }
    }

    private fun provideApis() = module {
        single { ApiProvider.transactionApi(get()) }
    }
}
