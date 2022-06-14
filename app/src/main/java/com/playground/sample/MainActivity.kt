package com.playground.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.playground.sample.databinding.ActivityMainBinding
import com.playground.transaction.TransactionLauncher
import com.playground.transaction.compose.TransactionComposeLauncher

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val appId by lazy { BuildConfig.APP_ID }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSendTransaction.setOnClickListener { startSendTransaction() }
        binding.buttonSendTransactionCompose.setOnClickListener { startSendTransactionCompose() }
    }

    private fun startSendTransaction() {
        val transactionLauncher = TransactionLauncher.create(appId)
        transactionLauncher.launchSendTransaction(this)
    }

    private fun startSendTransactionCompose() {
        val transactionComposeLauncher = TransactionComposeLauncher.create(appId)
        transactionComposeLauncher.launchSendTransaction(this)
    }
}
