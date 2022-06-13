package com.playground.data.executor

import com.playground.data.exception.BackendException
import com.playground.data.utils.FileReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Headers
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class SourceExecutorTest {

    private lateinit var sourceExecutor: SourceExecutor

    @Before
    fun setup() {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        sourceExecutor = SourceExecutor(Dispatchers.Unconfined, moshi)
    }

    @Test
    fun `on execute when request succeed then should build SourceResult accordingly`() {
        // arrange
        val headers = Headers.Builder()
            .add("token", "1234")
            .build()
        val response = Response.success(Unit, headers)

        // act
        val result = runBlocking { sourceExecutor.execute { simulateRequest(response) } }

        // assert
        assertEquals(true, result.isSuccessful)
        assertEquals(Unit, result.body)
        assertEquals(200, result.code)
        assertEquals("1234", result.headers["token"]!!.first())
        assertEquals(null, result.throwable)
    }

    @Test
    fun `on execute when request not succeed then should build SourceResult accordingly`() {
        // arrange
        val errorJson = FileReader.read("raw/error_body_mock.json")
        val errorBody = errorJson.toResponseBody(null)
        val response = Response.error<Unit>(400, errorBody)

        // act
        val result = runBlocking { sourceExecutor.execute { simulateRequest(response) } }

        // assert
        val backendException = result.throwable as BackendException
        assertEquals(false, result.isSuccessful)
        assertEquals(null, result.body)
        assertEquals("MOCK_ERROR", backendException.errorType)
        assertEquals("This mock data error behaviour.", backendException.message)
        assertEquals(400, result.code)
        assertEquals(true, result.headers.isEmpty())
    }

    @Test
    fun `on execute when a HttpException is thrown then should build SourceResult accordingly`() {
        // arrange
        val errorJson = FileReader.read("raw/error_body_mock.json")
        val errorBody = errorJson.toResponseBody(null)
        val response = Response.error<Unit>(500, errorBody)
        val exception = HttpException(response)

        // act
        val result = runBlocking { sourceExecutor.execute { simulateRequest(exception) } }

        // assert
        val backendException = result.throwable as BackendException
        assertEquals(false, result.isSuccessful)
        assertEquals(null, result.body)
        assertEquals("MOCK_ERROR", backendException.errorType)
        assertEquals("This mock data error behaviour.", backendException.message)
        assertEquals(500, result.code)
        assertEquals(true, result.headers.isEmpty())
    }

    @Test
    fun `on execute when an IOException is thrown then should build SourceResult accordingly`() {
        // arrange
        val exception = IOException()

        // act
        val result = runBlocking { sourceExecutor.execute { simulateRequest(exception) } }

        // assert
        assertEquals(false, result.isSuccessful)
        assertEquals(null, result.body)
        assertEquals(null, result.code)
        assertEquals(true, result.headers.isEmpty())
        assertEquals(true, result.throwable is IOException)
    }

    private suspend fun <T> simulateRequest(result: Response<T>): Response<T> {
        delay(10)
        return result
    }

    private suspend fun simulateRequest(throwable: Throwable): Response<Unit> {
        delay(10)
        throw throwable
    }
}
