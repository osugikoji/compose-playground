package com.penguinpay.sdk.sendtransaction

import android.content.Intent
import androidx.core.app.ComponentActivity
import com.penguinpay.sdk.sendtransaction.di.SdkInitializer
import com.penguinpay.sdk.sendtransaction.presentation.SendTransactionActivity

internal class PenguinPayLauncherImpl() : PenguinPayLauncher {

    override fun launchSendTransaction(activity: ComponentActivity) {
        SdkInitializer.stop()
        SdkInitializer.start(activity.applicationContext)
        val intent = Intent(activity, SendTransactionActivity::class.java)
        activity.startActivity(intent)
    }
}
