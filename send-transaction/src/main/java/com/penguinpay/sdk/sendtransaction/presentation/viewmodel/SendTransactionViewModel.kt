package com.penguinpay.sdk.sendtransaction.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguinpay.sdk.sendtransaction.domain.business.TransactionBusiness
import com.penguinpay.sdk.sendtransaction.domain.model.Country
import com.penguinpay.sdk.sendtransaction.domain.model.SendTransactionDataHolder
import com.penguinpay.sdk.sendtransaction.util.isFullName
import com.penguinpay.sdk.sendtransaction.util.isValidBinaryValue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class SendTransactionViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val transactionBusiness: TransactionBusiness
) : ViewModel() {

    val dataHolder = SendTransactionDataHolder()

    private val _enableButton = MutableLiveData(false)
    val enableButton: LiveData<Boolean> = _enableButton

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    fun setTransferValue(text: String) {
        dataHolder.transferValue = text
        _enableButton.value = text.isValidBinaryValue()
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
        transactionBusiness.getTransactionExchangedValue(transferValue, selectedCountry)
            .catch { _uiState.value = UIState.Error(it.message.orEmpty()) }
            .collect {
                _uiState.value = UIState.TransactionExchangedSuccess
                dataHolder.exchangedValue = it
            }
    }

    fun sendTransaction() = viewModelScope.launch(dispatcher) {
        _uiState.value = UIState.Loading
        transactionBusiness.sendTransaction(dataHolder)
            .catch { _uiState.value = UIState.Error(it.message.orEmpty()) }
            .collect { _uiState.value = UIState.SendTransactionSuccess }
    }

    sealed class UIState {
        object Loading : UIState()
        data class Error(val message: String) : UIState()
        object TransactionExchangedSuccess : UIState()
        object SendTransactionSuccess : UIState()
    }
}
