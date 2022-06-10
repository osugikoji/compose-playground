package com.penguinpay.sdk.sendtransaction.util

import com.penguinpay.sdk.sendtransaction.core.currencySymbolToBigDecimal
import java.math.BigDecimal



internal fun String.isFullName(): Boolean {
    if (this.isEmpty()) return  false
    val splitString = this.split(" ")
    val secondName = splitString.getOrNull(1).orEmpty()
    return secondName.isNotEmpty()
}