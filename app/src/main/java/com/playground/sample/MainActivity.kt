package com.playground.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.playground.sample.databinding.ActivityMainBinding
import com.playground.transaction.TransactionLauncher

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSendTransaction.setOnClickListener { startSendTransactionScreen() }
    }

    private fun startSendTransactionScreen() {
        val transactionLauncher = TransactionLauncher.create("e783f4d11f2b4ca0a78a1062f6e94f2e")
        transactionLauncher.launchSendTransaction(this)
    }
}
