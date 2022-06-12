package com.playground.data.di.module

import com.playground.data.di.provider.ApiProvider
import com.playground.data.executor.SourceExecutor
import com.playground.data.repository.TransactionRepository
import com.playground.data.repository.impl.TransactionRepositoryImpl
import org.koin.dsl.module

internal class DataSourceModule(private val appId: String) {

    fun provide() = provideRepositories().plus(provideApis()).plus(provideExecutor())

    private fun provideRepositories() = module {
        factory<TransactionRepository> { TransactionRepositoryImpl(appId, get(), get(), get()) }
    }

    private fun provideApis() = module {
        single { ApiProvider.transactionApi(get()) }
        single { ApiProvider.transactionMockApi(get()) }
    }

    private fun provideExecutor() = module {
        factory { SourceExecutor(moshi = get()) }
    }
}
