package com.playground.transaction.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

internal fun Activity.hideKeyboard(): Boolean {
    val inputMethod = Context.INPUT_METHOD_SERVICE
    val inputMethodManager = this.getSystemService(inputMethod) as InputMethodManager
    return inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}

internal fun EditText.showKeyboard(): Boolean {
    val inputMethod = Context.INPUT_METHOD_SERVICE
    val inputMethodManager = context.getSystemService(inputMethod) as InputMethodManager
    this.requestFocus()
    return inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
