package com.penguinpay.sdk.sendtransaction.presentation.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.penguinpay.sdk.sendtransaction.databinding.FragmentSelectRecipientCountryBinding
import com.penguinpay.sdk.sendtransaction.di.component.CustomKoinComponent
import com.penguinpay.sdk.sendtransaction.domain.model.Country
import com.penguinpay.sdk.sendtransaction.presentation.adapter.CountriesAdapter
import com.penguinpay.sdk.sendtransaction.presentation.viewmodel.SendTransactionViewModel
import com.penguinpay.sdk.sendtransaction.util.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

internal class SelectRecipientCountryFragment : Fragment(), CustomKoinComponent {

    private var _binding: FragmentSelectRecipientCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SendTransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectRecipientCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().hideKeyboard()
        setupCountryRecycler()
    }

    private fun setupCountryRecycler() {
        val adapter = CountriesAdapter(::onCountrySelected)
        val divider = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        binding.recyclerViewCountries.addItemDecoration(divider)
        binding.recyclerViewCountries.adapter = adapter
    }

    private fun onCountrySelected(country: Country) {
        viewModel.setSelectedCountry(country)
        val direction = SelectRecipientCountryFragmentDirections.toInsertRecipientPhone()
        findNavController().navigate(direction)
    }
}