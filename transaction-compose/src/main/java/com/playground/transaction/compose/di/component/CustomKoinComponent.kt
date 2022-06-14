package com.playground.transaction.compose.di.component

import com.playground.transaction.compose.di.TransactionComposeInitializer
import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface CustomKoinComponent : KoinComponent {

    override fun getKoin(): Koin {
        return TransactionComposeInitializer.get().koin
    }
}
