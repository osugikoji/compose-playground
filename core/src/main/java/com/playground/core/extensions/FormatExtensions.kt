package com.playground.core.extensions

import android.telephony.PhoneNumberUtils

private const val NON_DIGIT_REGEX = "[^\\d]"

fun String.formatToDigits(): String = this.replace(NON_DIGIT_REGEX.toRegex(), "")

fun String.formatToPhone(countryCodeIso: String): String {
    return runCatching { PhoneNumberUtils.formatNumber(this, countryCodeIso)!! }
        .getOrDefault(this)
}
