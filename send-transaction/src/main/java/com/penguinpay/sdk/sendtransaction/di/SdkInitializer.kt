package com.penguinpay.sdk.sendtransaction.di

import android.content.Context
import com.penguinpay.sdk.sendtransaction.data.repository.TransactionRepository
import com.penguinpay.sdk.sendtransaction.di.provider.ApiProvider
import com.penguinpay.sdk.sendtransaction.di.provider.NetworkProvider
import com.penguinpay.sdk.sendtransaction.domain.business.TransactionUseCases
import com.penguinpay.sdk.sendtransaction.presentation.viewmodel.SendTransactionViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module

internal object SdkInitializer {

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
        return koinApp ?: error("KoinApplication for PenguinPaySDK has not been started.")
    }

    private fun buildModule() = module {
        viewModel { SendTransactionViewModel(Dispatchers.Main, get()) }
        factory { TransactionUseCases(get()) }
        factory { TransactionRepository(get()) }

        single { NetworkProvider.moshi() }
        single { NetworkProvider.httpClient() }
        single { NetworkProvider.retrofit(get(), get()) }

        single { ApiProvider.transactionApi(get()) }
    }
}