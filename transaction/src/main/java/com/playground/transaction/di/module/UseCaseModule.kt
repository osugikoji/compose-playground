package com.playground.transaction.di.module

import com.playground.transaction.domain.business.TransactionUseCases
import org.koin.dsl.module

internal class UseCaseModule {

    fun provide() = module {
        factory { TransactionUseCases(get()) }
    }
}
