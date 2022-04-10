package com.penguinpay.sdk.sendtransaction.presentation.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.penguinpay.sdk.sendtransaction.databinding.FragmentInsertRecipientNameBinding
import com.penguinpay.sdk.sendtransaction.di.component.CustomKoinComponent
import com.penguinpay.sdk.sendtransaction.presentation.viewmodel.SendTransactionViewModel
import com.penguinpay.sdk.sendtransaction.util.showKeyboard
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

internal class InsertRecipientNameFragment : Fragment(), CustomKoinComponent {

    private var _binding: FragmentInsertRecipientNameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SendTransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertRecipientNameBinding.inflate(inflater, container, false)
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
        binding.editTextName.showKeyboard()
        val name = viewModel.dataHolder.recipientName
        binding.editTextName.setText(name)
        viewModel.setRecipientName(name)
    }

    private fun setListeners() = with(binding) {
        editTextName.addTextChangedListener { viewModel.setRecipientName(it.toString()) }
    }
}
