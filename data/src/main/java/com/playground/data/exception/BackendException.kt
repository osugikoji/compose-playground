package com.playground.data.exception

class BackendException(
    val errorType: String? = null,
    errorMessage: String? = null,
) : RuntimeException(errorMessage)
