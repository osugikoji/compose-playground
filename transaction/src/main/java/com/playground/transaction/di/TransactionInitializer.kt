package com.playground.transaction.di

import android.content.Context
import com.playground.domain.di.DomainModuleFactory
import com.playground.transaction.di.module.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

internal object TransactionInitializer {

    private var koinApp: KoinApplication? = null

    fun start(context: Context, appId: String, baseUrl: String) {
        koinApp = koinApplication {
            androidContext(context.applicationContext)
            modules(
                DomainModuleFactory.buildModules(appId, baseUrl)
                    .plus(ViewModelModule().provide())
            )
        }
    }

    fun stop() {
        koinApp?.close()
        koinApp = null
    }

    fun get(): KoinApplication {
        return koinApp ?: error("KoinApplication for Transaction has not been started.")
    }
}
