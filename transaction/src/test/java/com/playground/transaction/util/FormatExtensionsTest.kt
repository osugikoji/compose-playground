package com.playground.transaction.util

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatExtensionsTest {

    @Test
    fun `on formatToDigits should return string with only digits`() {
        assertEquals("19982252031", "(19) 98225-2031".formatToDigits())
    }

    @Test
    fun `on binaryToDecimal should return binary value`() {
        assertEquals(22, "010110".binaryToDecimal())
    }

    @Test
    fun `on decimalToBinary should return decimal value`() {
        assertEquals("10110", 22.toDouble().toBinary())
    }
}