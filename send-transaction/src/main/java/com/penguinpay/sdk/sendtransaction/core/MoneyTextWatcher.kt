package com.penguinpay.sdk.sendtransaction.core

import android.text.Editable
import android.text.TextWatcher
import java.util.Locale

internal class MoneyTextWatcher(
    private val locale: Locale = Locale.US
) : TextWatcher {

    private var editing = false
    private var updatedText: String? = null

    override fun beforeTextChanged(
        charSequence: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        // Intentionally not implemented
    }

    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        if (text.toString() == updatedText || editing) return
        val parsed = text.toString().currencySymbolToBigDecimal(locale)
        updatedText = parsed.toCurrencySymbol(locale)
    }

    override fun afterTextChanged(editable: Editable) {
        if (editing) return
        val filters = editable.filters
        editable.filters = emptyArray()
        editing = true
        editable.clear()
        editable.insert(0, updatedText)
        editable.filters = filters
        editing = false
    }
}
