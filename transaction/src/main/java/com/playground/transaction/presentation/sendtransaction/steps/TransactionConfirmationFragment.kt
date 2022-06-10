package com.playground.transaction.presentation.sendtransaction.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.playground.transaction.R
import com.playground.transaction.databinding.FragmentTransactionConfirmationBinding
import com.playground.transaction.di.component.CustomKoinComponent
import com.playground.transaction.presentation.sendtransaction.viewmodel.SendTransactionViewModel
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
        val recipientName = viewModel.summary.recipientName
        val fullPhoneNumber = viewModel.summary.fullPhoneNumber
        val exchangedValue = viewModel.summary.exchangedValue

        binding.textViewTransferValue.text = viewModel.summary.transferValue
        binding.textViewTransferTo.text =
            getString(R.string.transaction_confirmation_transfer_to, recipientName)
        binding.imageViewCountry.setImageResource(viewModel.summary.countryIcon)
        binding.textViewCountryValue.setText(viewModel.summary.countryName)
        binding.textViewPhoneValue.text = fullPhoneNumber
        binding.textViewTransferExchangeValue.text = exchangedValue
    }
}