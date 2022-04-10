package com.penguinpay.sdk.sendtransaction

import androidx.core.app.ComponentActivity

interface PenguinPayLauncher {

    fun launchSendTransaction(activity: ComponentActivity)

    companion object {

        fun create(): PenguinPayLauncher {
            return PenguinPayLauncherImpl()
        }
    }
}

