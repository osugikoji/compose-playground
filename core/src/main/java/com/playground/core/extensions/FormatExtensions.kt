package com.playground.core.extensions

private const val NON_DIGIT_REGEX = "[^\\d]"

fun String.formatToDigits(): String = this.replace(NON_DIGIT_REGEX.toRegex(), "")
