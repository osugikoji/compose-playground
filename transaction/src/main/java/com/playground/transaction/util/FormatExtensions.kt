package com.playground.transaction.util

private const val NON_DIGIT_REGEX = "[^\\d]"
private const val BINARY_BASE = 2

internal fun String.formatToDigits(): String = this.replace(NON_DIGIT_REGEX.toRegex(), "")

internal fun String.binaryToDecimal(): Int {
    return Integer.parseInt(this, BINARY_BASE)
}

internal fun Double.toBinary(): String {
    return Integer.toBinaryString(this.toInt())
}