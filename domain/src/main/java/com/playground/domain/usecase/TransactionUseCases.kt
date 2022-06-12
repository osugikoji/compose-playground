package com.playground.domain.usecase

import com.playground.core.extensions.formatToDigits
import com.playground.data.remote.dto.SendTransactionDto
import com.playground.data.repository.TransactionRepository
import com.playground.domain.model.CurrencyCode
import java.math.BigDecimal
import kotlin.math.absoluteValue

class TransactionUseCases(private val transactionRepository: TransactionRepository) {

    suspend fun getExchangedValue(
        transferValue: BigDecimal,
        currencySymbol: String,
    ): BigDecimal {
        val exchangeResult =
            transactionRepository.getExchange(CurrencyCode.UNITED_STATES, currencySymbol)
        val exchangeDto = exchangeResult.getBodyOrThrow()
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
            baseValue = CurrencyCode.UNITED_STATES,
            value = transferValue.toString(),
            recipientCurrency = recipientCurrency,
            phoneNumber = sanitizedPhone
        )
        val result = transactionRepository.sendTransaction(requestDto)
        result.ensureSuccess()
    }
}
