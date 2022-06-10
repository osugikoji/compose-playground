package com.playground.transaction.di.module

import com.playground.transaction.di.provider.NetworkProvider
import org.koin.dsl.module

internal class NetworkModule {

    fun provide() = module {
        single { NetworkProvider.moshi() }
        single { NetworkProvider.httpClient() }
        single { NetworkProvider.retrofit(get(), get()) }
    }
}
