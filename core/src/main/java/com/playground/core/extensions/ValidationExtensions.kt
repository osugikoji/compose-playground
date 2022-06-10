package com.playground.core.extensions

fun String.isFullName(): Boolean {
    if (this.isEmpty()) return false
    val splitString = this.split(" ")
    val secondName = splitString.getOrNull(1).orEmpty()
    return secondName.isNotEmpty()
}
