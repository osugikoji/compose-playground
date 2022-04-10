package com.penguinpay.sdk.sendtransaction.di.component

import com.penguinpay.sdk.sendtransaction.di.SdkInitializer
import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface CustomKoinComponent: KoinComponent {

    override fun getKoin(): Koin {
        return SdkInitializer.get().koin
    }
}