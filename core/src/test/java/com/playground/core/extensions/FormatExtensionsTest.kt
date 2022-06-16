package com.playground.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE, qualifiers = "pt-rBR")
@RunWith(value = RobolectricTestRunner::class)
class FormatExtensionsTest {

    @Test
    fun `on formatToDigits should return string with only digits`() {
        assertEquals("19982252031", "(19) 98225-2031".formatToDigits())
    }

    @Test
    fun `on formatToPhone should return formatted phone`() {
        assertEquals("(19) 3333-6666", "1933336666".formatToPhone("BR"))
    }

    @Test
    fun `on formatToPhone when exception is thrown then should return current phone`() {
        assertEquals("1933336666", "1933336666".formatToPhone("@!"))
    }
}
