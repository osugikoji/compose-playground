package com.penguinpay.sdk.sendtransaction.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.penguinpay.sdk.sendtransaction.R
import com.penguinpay.sdk.sendtransaction.component.DialogLoading
import com.penguinpay.sdk.sendtransaction.databinding.ActivitySendTransactionBinding
import com.penguinpay.sdk.sendtransaction.di.component.CustomKoinComponent
import com.penguinpay.sdk.sendtransaction.presentation.steps.InsertRecipientNameFragmentDirections
import com.penguinpay.sdk.sendtransaction.presentation.steps.InsertRecipientPhoneFragmentDirections
import com.penguinpay.sdk.sendtransaction.presentation.steps.InsertTransactionValueFragmentDirections
import com.penguinpay.sdk.sendtransaction.presentation.steps.SelectRecipientCountryFragmentDirections
import com.penguinpay.sdk.sendtransaction.presentation.viewmodel.SendTransactionViewModel
import com.penguinpay.sdk.sendtransaction.presentation.viewmodel.SendTransactionViewModel.UIState
import com.penguinpay.sdk.sendtransaction.util.popBackStackOrFinish
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SendTransactionActivity :
    AppCompatActivity(),
    CustomKoinComponent,
    NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivitySendTransactionBinding
    private lateinit var navController: NavController

    private val viewModel: SendTransactionViewModel by viewModel()
    private val dialogLoading by lazy { DialogLoading(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendTransactionBinding.inflate(layoutInflater)
        setupNavController()
        setContentView(binding.root)
        setListeners()
        setObservables()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        val showButton = destination.id != R.id.select_recipient_country_fragment
        binding.buttonNavigation.isVisible = showButton

        if (destination.id == R.id.transaction_confirmation_fragment) {
            binding.buttonNavigation.setText(R.string.send_transaction_transfer)
        } else {
            binding.buttonNavigation.setText(R.string.send_transaction_next)
        }
    }

    private fun setupNavController() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHost.navController
        navController.addOnDestinationChangedListener(this)
    }

    private fun setListeners() {
        binding.toolbar.setNavigationOnClickListener { navController.popBackStackOrFinish(this) }
        binding.toolbar.setOnMenuItemClickListener { onMenuItemClick(it) }
        binding.buttonNavigation.setOnClickListener { onNextScreen() }
    }

    private fun setObservables() {
        viewModel.enableButton.observe(this) { binding.buttonNavigation.isEnabled = it }
        viewModel.uiState.observe(this) { handleUiState(it) }
    }

    private fun handleUiState(uiState: UIState) {
        dialogLoading.setLoading(uiState is UIState.Loading)
        when (uiState) {
            is UIState.TransactionExchangedSuccess -> {
                val direction = InsertRecipientPhoneFragmentDirections.toTransactionConfirmation()
                navController.navigate(direction)
            }
            is UIState.Error -> {
                Snackbar
                    .make(binding.root, uiState.message, Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getColor(R.color.status_error_main))
                    .show()
            }
            UIState.SendTransactionSuccess -> {
                redirectToSuccessScreen()
            }
            is UIState.Loading -> Unit
        }
    }

    private fun onNextScreen() {
        when (navController.currentDestination?.id) {
            R.id.insert_transaction_fragment -> {
                val direction = InsertTransactionValueFragmentDirections.toInsertRecipientName()
                navController.navigate(direction)
            }
            R.id.insert_recipient_name_fragment -> {
                val direction = InsertRecipientNameFragmentDirections.toSelectRecipientCountry()
                navController.navigate(direction)
            }
            R.id.select_recipient_country_fragment -> {
                val direction = SelectRecipientCountryFragmentDirections.toInsertRecipientPhone()
                navController.navigate(direction)
            }
            R.id.insert_recipient_phone_fragment -> {
                viewModel.getTransactionExchangedValue()
            }
            R.id.transaction_confirmation_fragment -> {
                viewModel.sendTransaction()
            }
        }
    }

    private fun redirectToSuccessScreen() {
        val transferValue = viewModel.dataHolder.getTransferValueAsCurrency()
        val intent = TransactionSuccessActivity.getIntent(this, transferValue)
        startActivity(intent)
        finish()
    }

    private fun onMenuItemClick(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.close) {
            finish()
            return true
        }
        return false
    }
}

