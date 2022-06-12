package com.playground.domain.usecase

import com.playground.data.executor.SourceResult
import com.playground.data.remote.dto.ExchangeDto
import com.playground.data.repository.TransactionRepository
import io.mockk.coEvery
import io.mockk.mockk
import java.math.BigDecimal
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TransactionUseCasesTest {

    private lateinit var useCases: TransactionUseCases

    private val transactionRepository = mockk<TransactionRepository>()

    @Before
    fun setup() {
        useCases = TransactionUseCases(transactionRepository)
    }

    @Test
    fun `on getExchangedValue when request succeed then should return exchanged value`() {
        // arrange
        val transactionValue = BigDecimal(100)
        val currencySymbol = "BRL"
        val sourceResult =
            SourceResult(true, body = ExchangeDto("USD", mapOf(currencySymbol to 5.00)))
        coEvery { transactionRepository.getExchange("USD", currencySymbol) } returns sourceResult

        // act
        val result = runBlocking { useCases.getExchangedValue(transactionValue, currencySymbol) }

        // assert
        assertEquals("500.0".toBigDecimal(), result)
    }

    @Test(expected = IllegalStateException::class)
    fun `on getExchangedValue when request failed then should throw an exception`() {
        // arrange
        val transactionValue = BigDecimal(100)
        val currencySymbol = "BRL"
        val sourceResult = SourceResult<ExchangeDto>(false, code = 400)
        coEvery { transactionRepository.getExchange("USD", currencySymbol) } returns sourceResult

        // act
        runBlocking { useCases.getExchangedValue(transactionValue, currencySymbol) }
    }

    @Test(expected = IllegalStateException::class)
    fun `on getExchangedValue when could not find currency symbol from response then should throw an exception`() {
        // arrange
        val transactionValue = BigDecimal(100)
        val currencySymbol = "BRL"
        val sourceResult = SourceResult(true, body = ExchangeDto("USD", mapOf("NGN" to 361.50)))
        coEvery { transactionRepository.getExchange(any(), any()) } returns sourceResult

        // act
        runBlocking { useCases.getExchangedValue(transactionValue, currencySymbol) }
    }

    @Test
    fun `on sendTransaction when request succeed should return unit`() = runBlocking {
        // arrange
        val transferValue = BigDecimal(100)
        val phoneNumber = "+55 19 9825-6899"
        val recipientCurrency = "BRL"
        coEvery { transactionRepository.sendTransaction(any()) } returns SourceResult(true)

        // act
        val result = useCases.sendTransaction(transferValue, phoneNumber, recipientCurrency)

        // assert
        assertEquals(Unit, result)
    }

    @Test(expected = IllegalStateException::class)
    fun `on sendTransaction when request failed should throw IllegalStateException`() =
        runBlocking {
            // arrange
            val transferValue = BigDecimal(100)
            val phoneNumber = "+55 19 9825-6899"
            val recipientCurrency = "BRL"
            val sourceResult = SourceResult<Unit>(false, code = 400)
            coEvery { transactionRepository.sendTransaction(any()) } returns sourceResult

            // act
            useCases.sendTransaction(transferValue, phoneNumber, recipientCurrency)
        }
}
