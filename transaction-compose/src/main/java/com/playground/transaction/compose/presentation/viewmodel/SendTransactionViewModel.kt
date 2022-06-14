package com.playground.transaction.compose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.playground.core.extensions.toCurrencyFormat
import com.playground.domain.model.CurrencyCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class SendTransactionViewModel : ViewModel() {

    private val _transactionValue = MutableStateFlow("0".toCurrencyFormat(CurrencyCode.BRAZIL))
    val transactionValue: StateFlow<String> = _transactionValue.asStateFlow()

    private val _insertTransferName = MutableStateFlow("")
    val insertTransferName: StateFlow<String> = _insertTransferName.asStateFlow()

    private val _enableButton = MutableStateFlow(false)
    val enableButton: StateFlow<Boolean> = _enableButton

    fun setTransferValue(text: String) {
        _transactionValue.value = text.toCurrencyFormat(CurrencyCode.BRAZIL)
        _enableButton.value = text.isNotEmpty()
    }

    fun setInsertTransferName(text: String) {
        _insertTransferName.value = text
        _enableButton.value = text.isNotEmpty()
    }
}
