package com.playground.transaction

import android.content.Intent
import androidx.core.app.ComponentActivity
import com.playground.transaction.di.TransactionInitializer
import com.playground.transaction.presentation.sendtransaction.SendTransactionActivity

internal class TransactionLauncherImpl(private val appId: String) : TransactionLauncher {

    override fun launchSendTransaction(activity: ComponentActivity) {
        TransactionInitializer.stop()
        TransactionInitializer.start(activity.applicationContext, appId, BASE_URL)
        val intent = Intent(activity, SendTransactionActivity::class.java)
        activity.startActivity(intent)
    }

    companion object {
        private const val BASE_URL = "https://openexchangerates.org/"
    }
}
