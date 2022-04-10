package com.penguinpay.sdk.sendtransaction.presentation.steps

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.penguinpay.sdk.sendtransaction.databinding.FragmentInsertRecipientPhoneBinding
import com.penguinpay.sdk.sendtransaction.di.component.CustomKoinComponent
import com.penguinpay.sdk.sendtransaction.presentation.viewmodel.SendTransactionViewModel
import com.penguinpay.sdk.sendtransaction.util.showKeyboard
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

internal class InsertRecipientPhoneFragment : Fragment(), CustomKoinComponent {

    private var _binding: FragmentInsertRecipientPhoneBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SendTransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertRecipientPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoreState()
        setListeners()
    }

    private fun restoreState() {
        binding.editTextPhone.showKeyboard()
        val phonePrefix = viewModel.dataHolder.selectedCountry.phonePrefix
        val phone = viewModel.dataHolder.phoneNumber
        binding.editTextPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.editTextPhonePrefix.setText(phonePrefix)
        binding.editTextPhone.setText(phone)
    }

    private fun setListeners() = with(binding) {
        editTextPhone.addTextChangedListener { viewModel.setRecipientPhone(it.toString()) }
    }
}