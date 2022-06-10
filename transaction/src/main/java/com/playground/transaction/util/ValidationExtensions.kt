package com.playground.transaction.util

internal fun String.isFullName(): Boolean {
    if (this.isEmpty()) return  false
    val splitString = this.split(" ")
    val secondName = splitString.getOrNull(1).orEmpty()
    return secondName.isNotEmpty()
}
