package com.playground.transaction.presentation.sendtransaction.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.playground.transaction.core.MoneyTextWatcher
import com.playground.transaction.databinding.FragmentInsertTransactionValueBinding
import com.playground.transaction.di.component.CustomKoinComponent
import com.playground.transaction.presentation.sendtransaction.viewmodel.SendTransactionViewModel
import com.playground.transaction.util.showKeyboard
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

internal class InsertTransactionValueFragment : Fragment(), CustomKoinComponent {

    private var _binding: FragmentInsertTransactionValueBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SendTransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertTransactionValueBinding.inflate(inflater, container, false)
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
        val transferValue = viewModel.dataHolder.getTransferValueAsCurrency()
        binding.editTextTransactionValue.addTextChangedListener(MoneyTextWatcher())
        binding.editTextTransactionValue.showKeyboard()
        binding.editTextTransactionValue.setText(transferValue)
        viewModel.setTransferValue(transferValue)
    }

    private fun setListeners() = with(binding) {
        editTextTransactionValue.addTextChangedListener { viewModel.setTransferValue(it.toString()) }
    }
}
