package com.playground.transaction.domain.business

import com.playground.transaction.data.remote.dto.SendTransactionDto
import com.playground.transaction.data.repository.TransactionRepository
import com.playground.transaction.domain.model.CurrencySymbol
import com.playground.transaction.util.formatToDigits
import java.math.BigDecimal
import kotlin.math.absoluteValue

internal class TransactionUseCases(private val transactionRepository: TransactionRepository) {

    suspend fun getExchangedValue(
        transferValue: BigDecimal,
        currencySymbol: String,
    ): BigDecimal {
        val exchangeResult =
            transactionRepository.getExchange(CurrencySymbol.UNITED_STATES, currencySymbol)
        val exchangeDto = exchangeResult.body() ?: error("Could not retrieved exchange result.")
        val rate = exchangeDto.rates[currencySymbol]?.absoluteValue
            ?: error("Could not find rate value for the given currency symbol.")

        return transferValue.multiply(rate.toBigDecimal())
    }

    suspend fun sendTransaction(
        transferValue: BigDecimal,
        phoneNumber: String,
        recipientCurrency: String,
    ) {
        val sanitizedPhone = phoneNumber.formatToDigits()
        val requestDto = SendTransactionDto(
            baseValue = CurrencySymbol.UNITED_STATES,
            value = transferValue.toString(),
            recipientCurrency = recipientCurrency,
            phoneNumber = sanitizedPhone
        )
        val result = transactionRepository.sendTransaction(requestDto)
        if (!result.isSuccessful) {
            throw IllegalStateException("Send transaction request failed.")
        }
    }
}
