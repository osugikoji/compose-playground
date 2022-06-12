package com.playground.data.executor

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
            val result = request()
            result.toSourceResult()
        } catch (httpException: HttpException) {
            httpException.toSourceResult()
        } catch (ioException: IOException) {
            ioException.toSourceResult()
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
        val errorBody = this.response()?.tryGetErrorBody()
        return SourceResult(
            isSuccessful = false,
            errorBody = errorBody,
            code = this.code(),
            headers = headers,
            throwable = this
        )
    }

    private fun <T> Response<T>.toSourceResult(): SourceResult<T> {
        val headers = this.headers().toMultimap()
        val throwable = if (!this.isSuccessful) HttpException(this) else null
        val errorBody = this.tryGetErrorBody()
        return SourceResult(
            isSuccessful = this.isSuccessful,
            body = this.body(),
            errorBody = errorBody,
            code = this.code(),
            headers = headers,
            throwable = throwable
        )
    }

    private fun <T> Response<T>.tryGetErrorBody(): SourceResult.ErrorBody? {
        val errorJson = this.errorBody()?.string()
        val errorBodyDto = runCatching {
            moshi.adapter(ErrorBodyDto::class.java).fromJson(errorJson!!)
        }.getOrNull() ?: return null
        return SourceResult.ErrorBody(
            errorType = errorBodyDto.errorType,
            description = errorBodyDto.description
        )
    }

    private data class ErrorBodyDto(
        @Json(name = "error_type")
        val errorType: String,
        @Json(name = "description")
        val description: String,
    )
}
