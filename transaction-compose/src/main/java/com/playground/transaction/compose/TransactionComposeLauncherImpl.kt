package com.playground.transaction.compose

import androidx.core.app.ComponentActivity
import com.playground.transaction.compose.di.TransactionComposeInitializer
import com.playground.transaction.compose.presentation.SendTransactionActivity

internal class TransactionComposeLauncherImpl(private val appId: String) : TransactionComposeLauncher {

    override fun launchSendTransaction(activity: ComponentActivity) {
        TransactionComposeInitializer.stop()
        TransactionComposeInitializer.start(activity.applicationContext, appId, BASE_URL)
        val intent = SendTransactionActivity.getIntent(activity)
        activity.startActivity(intent)
    }

    companion object {
        private const val BASE_URL = "https://openexchangerates.org/"
    }
}
