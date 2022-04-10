package com.penguinpay.sdk.sendtransaction.domain.business

import com.penguinpay.sdk.sendtransaction.data.remote.dto.SendTransactionDto
import com.penguinpay.sdk.sendtransaction.data.repository.TransactionRepository
import com.penguinpay.sdk.sendtransaction.domain.model.Country
import com.penguinpay.sdk.sendtransaction.domain.model.Currency
import com.penguinpay.sdk.sendtransaction.domain.model.SendTransactionDataHolder
import com.penguinpay.sdk.sendtransaction.util.formatToDigits
import com.penguinpay.sdk.sendtransaction.util.toBinary
import com.penguinpay.sdk.sendtransaction.util.binaryToDecimal
import kotlin.math.absoluteValue
import kotlinx.coroutines.flow.flow

internal class TransactionBusiness(private val transactionRepository: TransactionRepository) {

    fun getTransactionExchangedValue(value: String, country: Country) = flow {
        val decimalValue = value.binaryToDecimal()
        val exchangeResult =
            transactionRepository.getExchange(Currency.UNITED_STATES, country.currencySymbol)
        val exchangeDto = exchangeResult.body() ?: error("Could not retrieved exchange result.")
        val rate = exchangeDto.rates[country.currencySymbol]?.absoluteValue
            ?: error("Could not find rate value for the given currency symbol.")
        val exchangedValue = decimalValue * rate
        emit(exchangedValue.toBinary())
    }

    fun sendTransaction(sendTransactionDataHolder: SendTransactionDataHolder) = flow {
        val sanitizedPhone = sendTransactionDataHolder.phoneNumber.formatToDigits()
        val requestDto = SendTransactionDto(
            Currency.UNITED_STATES,
            sendTransactionDataHolder.transferValue,
            sendTransactionDataHolder.selectedCountry.currencySymbol,
            sanitizedPhone
        )
        val result = transactionRepository.sendTransaction(requestDto)
        if (result.isSuccessful) {
            emit(Unit)
        } else {
            throw IllegalStateException("Send transaction request failed.")
        }
    }
}