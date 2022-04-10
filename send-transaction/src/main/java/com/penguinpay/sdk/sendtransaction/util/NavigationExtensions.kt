package com.penguinpay.sdk.sendtransaction.util

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController

internal fun NavController.popBackStackOrFinish(activity: AppCompatActivity): Boolean {
    return if (popBackStack()) {
        true
    } else {
        activity.finish()
        true
    }
}