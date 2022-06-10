package com.playground.transaction.presentation.sendtransaction.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.playground.transaction.R
import com.playground.transaction.databinding.FragmentInsertRecipientNameBinding
import com.playground.transaction.di.component.CustomKoinComponent
import com.playground.transaction.presentation.sendtransaction.viewmodel.SendTransactionViewModel
import com.playground.transaction.util.showKeyboard
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
        val name = viewModel.dataHolder.recipientName
        val transferValue = viewModel.dataHolder.getTransferValueAsCurrency()
        val formattedTransferValue = getString(R.string.insert_recipient_name_title, transferValue)
        binding.textViewTitle.text = formattedTransferValue
        binding.editTextName.showKeyboard()
        binding.editTextName.setText(name)
        viewModel.setRecipientName(name)
    }

    private fun setListeners() = with(binding) {
        editTextName.addTextChangedListener { viewModel.setRecipientName(it.toString()) }
    }
}
