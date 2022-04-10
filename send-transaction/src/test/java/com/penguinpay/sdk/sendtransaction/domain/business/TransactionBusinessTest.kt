package com.penguinpay.sdk.sendtransaction.domain.business

import com.penguinpay.sdk.sendtransaction.data.remote.dto.ExchangeDto
import com.penguinpay.sdk.sendtransaction.data.repository.TransactionRepository
import com.penguinpay.sdk.sendtransaction.domain.model.Country
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class TransactionBusinessTest {

    private lateinit var business: TransactionBusiness

    private val transactionRepository = mockk<TransactionRepository>()

    @Before
    fun setup() {
        business = TransactionBusiness(transactionRepository)
    }

    @Test
    fun `on getTransactionExchangedValue when request succeed then should return exchanged value`() {
        // arrange
        val transactionValue = "010110"
        val selectedCountry = Country.NIGERIA
        val response = Response.success(ExchangeDto("USD", mapOf("NGN" to 361.50)))
        coEvery { transactionRepository.getExchange("USD", "NGN") } returns response

        // act
        val result = business.getTransactionExchangedValue(transactionValue, selectedCountry)
        val exchangedValue = runBlocking { result.first() }

        // assert
        assertEquals("1111100010001", exchangedValue)
    }

    @Test(expected = IllegalStateException::class)
    fun `on getTransactionExchangedValue when request failed then should throw an exception`() {
        // arrange
        val transactionValue = "010110"
        val selectedCountry = Country.NIGERIA
        val response = Response.error<ExchangeDto>(400, ResponseBody.create(null, ""))
        coEvery { transactionRepository.getExchange("USD", "NGN") } returns response

        // act
        val result = business.getTransactionExchangedValue(transactionValue, selectedCountry)
        runBlocking { result.first() }
    }

    @Test(expected = IllegalStateException::class)
    fun `on getTransactionExchangedValue when could not find currency symbol from response then should throw an exception`() {
        // arrange
        val transactionValue = "010110"
        val selectedCountry = Country.NONE
        val response = Response.success(ExchangeDto("USD", mapOf("NGN" to 361.50)))
        coEvery { transactionRepository.getExchange(any(), any()) } returns response

        // act
        val result = business.getTransactionExchangedValue(transactionValue, selectedCountry)
        runBlocking { result.first() }
    }
}