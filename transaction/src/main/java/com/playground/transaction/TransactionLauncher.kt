package com.playground.transaction

import androidx.core.app.ComponentActivity

interface TransactionLauncher {

    fun launchSendTransaction(activity: ComponentActivity)

    companion object {

        fun create(appId: String): TransactionLauncher {
            return TransactionLauncherImpl(appId)
        }
    }
}
