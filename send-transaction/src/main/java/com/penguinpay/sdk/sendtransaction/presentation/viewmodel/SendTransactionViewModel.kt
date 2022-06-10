package com.penguinpay.sdk.sendtransaction.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguinpay.sdk.sendtransaction.core.toCurrencySymbol
import com.penguinpay.sdk.sendtransaction.domain.business.TransactionUseCases
import com.penguinpay.sdk.sendtransaction.domain.model.Country
import com.penguinpay.sdk.sendtransaction.domain.model.SendTransactionDataHolder
import com.penguinpay.sdk.sendtransaction.presentation.model.SendTransactionSummary
import com.penguinpay.sdk.sendtransaction.util.isFullName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

internal class SendTransactionViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val transactionUseCase: TransactionUseCases,
) : ViewModel() {

    val dataHolder = SendTransactionDataHolder()

    lateinit var summary: SendTransactionSummary private set

    private val _enableButton = MutableLiveData(false)
    val enableButton: LiveData<Boolean> = _enableButton

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    fun setTransferValue(text: String) {
        dataHolder.setTransferValue(text)
        _enableButton.value = dataHolder.isTransferValueNotEmpty()
    }

    fun setRecipientName(text: String) {
        dataHolder.recipientName = text
        _enableButton.value = text.isFullName()
    }

    fun setSelectedCountry(country: Country) {
        if (dataHolder.selectedCountry != country) {
            dataHolder.phoneNumber = ""
        }
        _enableButton.value = dataHolder.isValidPhoneNumber()
        dataHolder.selectedCountry = country
    }

    fun setRecipientPhone(text: String) {
        dataHolder.phoneNumber = text
        _enableButton.value = dataHolder.isValidPhoneNumber()
    }

    fun getTransactionExchangedValue() = viewModelScope.launch(dispatcher) {
        val transferValue = dataHolder.transferValue
        val selectedCountry = dataHolder.selectedCountry
        _uiState.value = UIState.Loading
        runCatching {
            transactionUseCase.getExchangedValue(transferValue, selectedCountry.currencySymbol)
        }.onSuccess {
            summary = SendTransactionSummary(
                transferValue = dataHolder.getTransferValueAsCurrency(),
                recipientName = dataHolder.recipientName,
                countryName = selectedCountry.countryName,
                countryIcon = selectedCountry.icon,
                fullPhoneNumber = dataHolder.getFullPhoneNumber(),
                exchangedValue = it.toCurrencySymbol(selectedCountry.currencySymbol)
            )
            _uiState.value = UIState.TransactionExchangedSuccess
        }.onFailure { _uiState.value = UIState.Error(it.message.orEmpty()) }
    }

    fun sendTransaction() = viewModelScope.launch(dispatcher) {
        val transferValue = dataHolder.transferValue
        val fullPhoneNumber = dataHolder.getFullPhoneNumber()
        val currencySymbol = dataHolder.selectedCountry.currencySymbol
        _uiState.value = UIState.Loading
        runCatching {
            transactionUseCase.sendTransaction(transferValue, fullPhoneNumber, currencySymbol)
        }.onSuccess { _uiState.value = UIState.SendTransactionSuccess }
            .onFailure { _uiState.value = UIState.Error(it.message.orEmpty()) }
    }

    sealed class UIState {
        object Loading : UIState()
        data class Error(val message: String) : UIState()
        object TransactionExchangedSuccess : UIState()
        object SendTransactionSuccess : UIState()
    }
}
