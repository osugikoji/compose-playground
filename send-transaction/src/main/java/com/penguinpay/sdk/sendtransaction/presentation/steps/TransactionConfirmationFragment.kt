package com.penguinpay.sdk.sendtransaction.presentation.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.penguinpay.sdk.sendtransaction.R
import com.penguinpay.sdk.sendtransaction.databinding.FragmentTransactionConfirmationBinding
import com.penguinpay.sdk.sendtransaction.di.component.CustomKoinComponent
import com.penguinpay.sdk.sendtransaction.presentation.viewmodel.SendTransactionViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

internal class TransactionConfirmationFragment : Fragment(), CustomKoinComponent {

    private var _binding: FragmentTransactionConfirmationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SendTransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoreState()
    }

    private fun restoreState() {
        val transferValue = viewModel.dataHolder.transferValue
        val recipientName = viewModel.dataHolder.recipientName
        val country = requireNotNull(viewModel.dataHolder.selectedCountry)
        val fullPhoneNumber = viewModel.dataHolder.getFullPhoneNumber()
        val exchangedValue = viewModel.dataHolder.exchangedValue

        binding.textViewTransferValue.text =
            getString(R.string.transaction_confirmation_currency_value, transferValue)
        binding.textViewTransferTo.text =
            getString(R.string.transaction_confirmation_transfer_to, recipientName)
        binding.imageViewCountry.setImageResource(country.icon)
        binding.textViewCountryValue.setText(country.countryName)
        binding.textViewPhoneValue.text = fullPhoneNumber
        binding.textViewTransferExchangeValue.text = exchangedValue
    }
}