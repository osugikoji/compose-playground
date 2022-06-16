package com.playground.transaction.compose.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playground.core.extensions.currencyFormatToBigDecimal
import com.playground.core.extensions.toCurrencyFormat
import com.playground.domain.model.Country
import com.playground.domain.model.CurrencyCode
import com.playground.domain.usecase.TransactionUseCases
import com.playground.transaction.compose.presentation.navigation.SendTransactionRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class SendTransactionViewModel(
    private val transactionUseCase: TransactionUseCases,
) : ViewModel() {

    val transferValue = mutableStateOf("")

    val name = mutableStateOf("")

    val phone = mutableStateOf("")

    val exchangedMoney = mutableStateOf("")

    private val _hideTopBar = MutableStateFlow(false)
    val hideTopBar: StateFlow<Boolean> = _hideTopBar

    private val _navigation = MutableLiveData<SendTransactionRoute>()
    val navigation: LiveData<SendTransactionRoute> = _navigation

    private val _uiState = MutableStateFlow<UIState>(UIState.Idle)
    val uiState: StateFlow<UIState> = _uiState

    lateinit var selectedCountry: Country
        private set

    fun navigate(route: SendTransactionRoute) {
        _navigation.value = route
    }

    fun setSelectedCountry(country: Country) {
        if (::selectedCountry.isInitialized && country != selectedCountry) {
            phone.value = ""
        }
        this.selectedCountry = country
    }

    fun getFullPhoneNumber(): String {
        return "${selectedCountry.phonePrefix} ${phone.value}"
    }

    fun getExchangeValue() = viewModelScope.launch {
        _uiState.value = UIState.Loading
        val transferValue = transferValue.value
            .currencyFormatToBigDecimal(CurrencyCode.UNITED_STATES)
        val currencySymbol = selectedCountry.currencySymbol
        runCatching {
            transactionUseCase.getExchangedValue(transferValue, currencySymbol)
        }.onSuccess {
            _uiState.value = UIState.Idle
            exchangedMoney.value = it.toCurrencyFormat(currencySymbol)
            _navigation.value = SendTransactionRoute.SUMMARY
        }.onFailure {
            _uiState.value = UIState.Error(it.message.orEmpty())
        }
    }

    fun sendTransaction() = viewModelScope.launch {
        _uiState.value = UIState.Loading
        val transferValue =
            transferValue.value.currencyFormatToBigDecimal(CurrencyCode.UNITED_STATES)
        val fullPhoneNumber = getFullPhoneNumber()
        val currencySymbol = selectedCountry.currencySymbol
        runCatching {
            transactionUseCase.sendTransaction(transferValue, fullPhoneNumber, currencySymbol)
        }.onSuccess {
            redirectToSuccessScreen()
        }.onFailure {
            _uiState.value = UIState.Error(it.message.orEmpty())
        }
    }

    private fun redirectToSuccessScreen() {
        _hideTopBar.value = true
        _uiState.value = UIState.Idle
        _navigation.value = SendTransactionRoute.SUCCESS
    }

    sealed class UIState {
        object Loading : UIState()
        data class Error(val message: String) : UIState()
        object Idle : UIState()
    }
}
