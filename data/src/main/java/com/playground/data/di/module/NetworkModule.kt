package com.playground.data.di.module

import com.playground.data.di.provider.NetworkProvider
import org.koin.dsl.module

internal class NetworkModule(private val baseUrl: String) {

    fun provide() = module {
        single { NetworkProvider.moshi() }
        single { NetworkProvider.httpClient() }
        single { NetworkProvider.retrofit(baseUrl, get(), get()) }
        single { NetworkProvider.retroMock(get()) }
    }
}
