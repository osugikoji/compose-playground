package com.playground.data.executor

import com.playground.core.idling.EspressoIdlingResource
import com.playground.data.exception.BackendException
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

internal class SourceExecutor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val moshi: Moshi,
) {

    suspend fun <T> execute(request: suspend () -> Response<T>) = withContext(dispatcher) {
        try {
            EspressoIdlingResource.increment()
            val result = request()
            result.toSourceResult()
        } catch (httpException: HttpException) {
            httpException.toSourceResult()
        } catch (ioException: IOException) {
            ioException.toSourceResult()
        } finally {
            EspressoIdlingResource.decrement()
        }
    }

    private fun <T> Throwable.toSourceResult(): SourceResult<T> {
        return SourceResult(
            isSuccessful = false,
            throwable = this
        )
    }

    private fun <T> HttpException.toSourceResult(): SourceResult<T> {
        val headers = this.response()?.headers()?.toMultimap().orEmpty()
        val throwable = this.response()?.buildBackendException()
        return SourceResult(
            isSuccessful = false,
            code = this.code(),
            headers = headers,
            throwable = throwable
        )
    }

    private fun <T> Response<T>.toSourceResult(): SourceResult<T> {
        val headers = this.headers().toMultimap()
        val throwable = if (!this.isSuccessful) this.buildBackendException() else null
        return SourceResult(
            isSuccessful = this.isSuccessful,
            body = this.body(),
            code = this.code(),
            headers = headers,
            throwable = throwable
        )
    }

    private fun <T> Response<T>.buildBackendException(): BackendException {
        val errorJson = this.errorBody()?.string()
        val errorBodyDto = runCatching {
            moshi.adapter(ErrorBodyDto::class.java).fromJson(errorJson!!)
        }.getOrNull()
        return BackendException(errorBodyDto?.errorType, errorBodyDto?.description)
    }

    private data class ErrorBodyDto(
        @Json(name = "error_type")
        val errorType: String,
        @Json(name = "description")
        val description: String,
    )
}
