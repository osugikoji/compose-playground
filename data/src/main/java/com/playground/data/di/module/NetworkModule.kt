package com.playground.data.di.module

import com.playground.data.di.provider.NetworkProvider
import org.koin.dsl.module

internal class NetworkModule {

    fun provide() = module {
        single { NetworkProvider.moshi() }
        single { NetworkProvider.httpClient() }
        single { NetworkProvider.retrofit(get(), get()) }
    }
}
