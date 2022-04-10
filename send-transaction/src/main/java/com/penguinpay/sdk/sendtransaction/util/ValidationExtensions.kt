package com.penguinpay.sdk.sendtransaction.util

private const val BINARY_REGEX = "^[01]+$"

internal fun String.isValidBinaryValue(): Boolean {
    return BINARY_REGEX.toRegex().matches(this)
}

internal fun String.isFullName(): Boolean {
    if (this.isEmpty()) return  false
    val splitString = this.split(" ")
    val secondName = splitString.getOrNull(1).orEmpty()
    return secondName.isNotEmpty()
}