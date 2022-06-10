package com.playground.domain.di.module

import com.playground.domain.usecase.TransactionUseCases
import org.koin.dsl.module

internal class UseCaseModule {

    fun provide() = module {
        factory { TransactionUseCases(get()) }
    }
}
