package com.playground.transaction.business

import com.playground.transaction.data.remote.dto.ExchangeDto
import com.playground.transaction.data.repository.TransactionRepository
import com.playground.transaction.domain.business.TransactionUseCases
import io.mockk.coEvery
import io.mockk.mockk
import java.math.BigDecimal
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

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
        val response = Response.success(ExchangeDto("USD", mapOf(currencySymbol to 5.00)))
        coEvery { transactionRepository.getExchange("USD", currencySymbol) } returns response

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
        val response = Response.error<ExchangeDto>(400, ResponseBody.create(null, ""))
        coEvery { transactionRepository.getExchange("USD", currencySymbol) } returns response

        // act
        runBlocking { useCases.getExchangedValue(transactionValue, currencySymbol)}
    }

    @Test(expected = IllegalStateException::class)
    fun `on getExchangedValue when could not find currency symbol from response then should throw an exception`() {
        // arrange
        val transactionValue = BigDecimal(100)
        val currencySymbol = "BRL"
        val response = Response.success(ExchangeDto("USD", mapOf("NGN" to 361.50)))
        coEvery { transactionRepository.getExchange(any(), any()) } returns response

        // act
        runBlocking { useCases.getExchangedValue(transactionValue, currencySymbol)}
    }
}
