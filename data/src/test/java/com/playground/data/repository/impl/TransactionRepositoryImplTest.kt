package com.playground.data.repository.impl

import com.playground.data.di.DataModuleFactory
import com.playground.data.remote.dto.SendTransactionDto
import com.playground.data.repository.TransactionRepository
import com.playground.data.utils.FileReader
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class TransactionRepositoryImplTest : KoinTest {

    private val appId = "1234"

    private lateinit var mockWebServer: MockWebServer

    private val repository by inject<TransactionRepository>()

    @Before
    fun setup() {
        startKoin { modules(DataModuleFactory.buildModules(appId, "http://localhost:8607/")) }
        mockWebServer = MockWebServer()
        mockWebServer.start(8607)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        stopKoin()
    }

    @Test
    fun `on getExchange when request succeed then should deserialize object correctly`() {
        // arrange
        val exchangeJson = FileReader.read("raw/exchange_200.json")
        val response = buildMockResponse(exchangeJson)
        mockWebServer.enqueue(response)

        // act
        val result = runBlocking { repository.getExchange("USD", "BRL") }.getBodyOrThrow()

        // assert
        assertEquals("USD", result.base)
        assertEquals(4.987385, result.rates["BRL"])
    }

    @Test
    fun `on sendTransaction then should return unit`() {
        // arrange
        val requestBody = SendTransactionDto(
            baseValue = "USD",
            value = "100.00",
            recipientCurrency = "BRL",
            phoneNumber = "551998224569",
        )

        // act
        val result = runBlocking { repository.sendTransaction(requestBody) }.getBodyOrThrow()

        // assert
        assertEquals(Unit, result)
    }

    private fun buildMockResponse(body: String, code: Int = 200): MockResponse {
        return MockResponse()
            .setBody(body)
            .setResponseCode(code)
    }
}
