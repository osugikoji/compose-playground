package com.playground.transaction.compose

import androidx.core.app.ComponentActivity

interface TransactionComposeLauncher {

    fun launchSendTransaction(activity: ComponentActivity)

    companion object {

        fun create(appId: String): TransactionComposeLauncher {
            return TransactionComposeLauncherImpl(appId)
        }
    }
}
