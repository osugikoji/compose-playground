package com.playground.transaction.di.component

import com.playground.transaction.di.TransactionInitializer
import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface CustomKoinComponent : KoinComponent {

    override fun getKoin(): Koin {
        return TransactionInitializer.get().koin
    }
}
