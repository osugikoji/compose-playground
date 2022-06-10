package com.playground.core.textwatcher

import android.text.SpannableStringBuilder
import android.text.TextWatcher
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE, qualifiers = "pt-rBR")
@RunWith(value = RobolectricTestRunner::class)
class MoneyTextWatcherTest {

    @Test
    fun `on create text watcher when input a value then should format with expected currency format`() {
        // arrange
        val textWatcher = MoneyTextWatcher("BRL")
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
