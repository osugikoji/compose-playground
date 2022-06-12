package com.playground.data.executor

import android.accounts.NetworkErrorException
import android.os.NetworkOnMainThreadException
import org.junit.Assert.assertEquals
import org.junit.Test

class SourceResultTest {

    @Test(expected = IllegalStateException::class)
    fun `on getBodyOrThrow when body is null and not has throwable then an Exception should be thrown`() {
        // arrange
        val sourceResult = SourceResult<String>(isSuccessful = false)

        // act
        sourceResult.getBodyOrThrow()
    }

    @Test(expected = NetworkOnMainThreadException::class)
    fun `on getBodyOrThrow when body is null and has throwable then given throwable should be thrown`() {
        // arrange
        val throwable = NetworkOnMainThreadException()
        val sourceResult = SourceResult<String>(isSuccessful = false, throwable = throwable)

        // act
        sourceResult.getBodyOrThrow()
    }

    @Test
    fun `on getBodyOrThrow when body is not null then should retrieve body`() {
        // arrange
        val body = "Test result."
        val sourceResult = SourceResult(isSuccessful = false, body = body)

        // act
        val result = sourceResult.getBodyOrThrow()

        // assert
        assertEquals(body, result)
    }

    @Test(expected = IllegalStateException::class)
    fun `on ensureSuccess when is not successful and throwable is null then an Exception should be thrown`() {
        // arrange
        val sourceResult = SourceResult<Unit>(isSuccessful = false)

        // act
        sourceResult.ensureSuccess()
    }

    @Test(expected = NetworkErrorException::class)
    fun `on ensureSuccess when is not successful and throwable is not null then should throw given throwable`() {
        // arrange
        val expectedThrowable = NetworkErrorException()
        val sourceResult = SourceResult<Unit>(isSuccessful = false, throwable = expectedThrowable)

        // act
        sourceResult.ensureSuccess()
    }

    @Test
    fun `on ensureSuccess when is successful then an IllegalStateException should be not thrown`() {
        // arrange
        val sourceResult = SourceResult<Unit>(isSuccessful = true)

        // act
        val result = runCatching { sourceResult.ensureSuccess() }.exceptionOrNull()

        // assert
        assertEquals(null, result)
    }

    @Test
    fun `on onSuccess when is successful then should invoke lambda`() {
        // arrange
        var result: String? = null
        val sourceResult = SourceResult(true, "Body result.")

        // act
        sourceResult.onSuccess { result = it }

        // assert
        assertEquals("Body result.", result)
    }

    @Test
    fun `on onSuccess when is not successful then should not invoke lambda`() {
        // arrange
        var result: String? = null
        val sourceResult = SourceResult(false, "")

        // act
        sourceResult.onSuccess { result = it }

        // assert
        assertEquals(null, result)
    }

    @Test
    fun `on onError when is not successful then should invoke lambda`() {
        // arrange
        var result: Throwable? = null
        val sourceResult = SourceResult<Unit>(false, throwable = IllegalArgumentException())

        // act
        sourceResult.onError { result = it }

        // assert
        assertEquals(true, result is IllegalArgumentException)
    }

    @Test
    fun `on onError when is successful then should not invoke lambda`() {
        // arrange
        var result: Throwable? = null
        val sourceResult = SourceResult<Unit>(true, throwable = Exception())

        // act
        sourceResult.onError { result = it }

        // assert
        assertEquals(null, result)
    }

    @Test
    fun `check error body creation`() {
        // arrange
        val errorBody = SourceResult.ErrorBody("ERROR", "Error mock.")
        val sourceResult = SourceResult<Unit>(
            isSuccessful = false,
            errorBody = errorBody,
            throwable = Exception()
        )

        // assert
        assertEquals(errorBody, sourceResult.errorBody)
    }
}
