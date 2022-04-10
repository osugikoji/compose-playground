package com.penguinpay.prototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.penguinpay.prototype.databinding.ActivityMainBinding
import com.penguinpay.sdk.sendtransaction.PenguinPayLauncher

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSendTransaction.setOnClickListener { startSendTransactionScreen() }
    }

    private fun startSendTransactionScreen() {
        val penguinPayLauncher = PenguinPayLauncher.create()
        penguinPayLauncher.launchSendTransaction(this)
    }
}