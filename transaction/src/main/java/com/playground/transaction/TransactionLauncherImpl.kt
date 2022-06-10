package com.playground.transaction

import android.content.Intent
import androidx.core.app.ComponentActivity
import com.playground.transaction.di.TransactionInitializer
import com.playground.transaction.presentation.sendtransaction.SendTransactionActivity

internal class TransactionLauncherImpl : TransactionLauncher {

    override fun launchSendTransaction(activity: ComponentActivity) {
        TransactionInitializer.stop()
        TransactionInitializer.start(activity.applicationContext)
        val intent = Intent(activity, SendTransactionActivity::class.java)
        activity.startActivity(intent)
    }
}
