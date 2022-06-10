package com.playground.transaction

import android.text.SpannableStringBuilder
import android.text.TextWatcher
import com.playground.transaction.core.MoneyTextWatcher
import java.util.Locale
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE, qualifiers = "pt-rBR")
@RunWith(value = RobolectricTestRunner::class)
class MoneyTextWatcherTest {

    @Test
    fun `on create text watcher with default parameter when input a value then should format as expected`() {
        // arrange
        val textWatcher = MoneyTextWatcher()
        val originalInput = ""
        val newInput = "100"

        // act
        val result = textWatcher.simulateTextInput(originalInput, newInput)

        // arrange
        assertEquals("US$ 1,00", result)
    }

    @Test
    fun `on create text watcher with custom locale when input a value then should format with expected currency locale`() {
        // arrange
        val textWatcher = MoneyTextWatcher(Locale("pt", "BR"))
        val originalInput = ""
        val newInput = "100"

        // act
        val result = textWatcher.simulateTextInput(originalInput, newInput)

        // arrange
        assertEquals("R$ 1,00", result)
    }

    private fun TextWatcher.simulateTextInput(originalInput: String, newInput: String): String {
        val newText = originalInput + newInput
        val editable = SpannableStringBuilder(newText)

        this.beforeTextChanged(originalInput, 0, originalInput.length, originalInput.length)
        this.onTextChanged(newText, 1, newText.length - 1, 1)
        this.afterTextChanged(editable)
        return editable.toString()
    }
}
