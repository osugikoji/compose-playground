package com.playground.transaction.presentation.sendtransaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.playground.transaction.R
import com.playground.transaction.databinding.ActivityTransactionSuccessBinding

internal class TransactionSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val payload = intent.getStringExtra(PAYLOAD).orEmpty()
        val message = getString(R.string.transaction_success_message, payload)
        binding.textViewMessage.text = message
        binding.buttonAction.setOnClickListener { finish() }
    }

    companion object {

        private const val PAYLOAD = "PAYLOAD"

        fun getIntent(context: Context, value: String): Intent {
            return Intent(context, TransactionSuccessActivity::class.java).apply {
                putExtra(PAYLOAD, value)
            }
        }
    }
}
