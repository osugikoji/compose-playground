package com.playground.transaction.di

import android.content.Context
import com.playground.transaction.di.module.DataSourceModule
import com.playground.transaction.di.module.NetworkModule
import com.playground.transaction.di.module.UseCaseModule
import com.playground.transaction.di.module.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

internal object TransactionInitializer {

    private var koinApp: KoinApplication? = null

    fun start(context: Context) {
        koinApp = koinApplication {
            androidContext(context.applicationContext)
            modules(buildModule())
        }
    }

    fun stop() {
        koinApp?.close()
        koinApp = null
    }

    fun get(): KoinApplication {
        return koinApp ?: error("KoinApplication for Transaction has not been started.")
    }

    private fun buildModule() = NetworkModule().provide()
        .plus(DataSourceModule().provide())
        .plus(UseCaseModule().provide())
        .plus(ViewModelModule().provide())
}
