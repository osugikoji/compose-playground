package com.playground.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatExtensionsTest {

    @Test
    fun `on formatToDigits should return string with only digits`() {
        assertEquals("19982252031", "(19) 98225-2031".formatToDigits())
    }
}
