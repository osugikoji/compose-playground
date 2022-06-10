package com.playground.transaction

import androidx.core.app.ComponentActivity

interface TransactionLauncher {

    fun launchSendTransaction(activity: ComponentActivity)

    companion object {

        fun create(): TransactionLauncher {
            return TransactionLauncherImpl()
        }
    }
}

