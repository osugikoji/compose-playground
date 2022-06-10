package com.playground.transaction.di.module

import com.playground.transaction.data.repository.TransactionRepository
import com.playground.transaction.di.provider.ApiProvider
import org.koin.dsl.module

internal class DataSourceModule {

    fun provide() = provideRepositories().plus(provideApis())

    private fun provideRepositories() = module {
        factory { TransactionRepository(get()) }
    }

    private fun provideApis() = module {
        single { ApiProvider.transactionApi(get()) }
    }
}
