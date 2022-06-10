package com.playground.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class ValidationExtensionsTest {

    @Test
    fun `on isFullName when is empty value should return false`() {
        assertEquals(false, "".isFullName())
    }

    @Test
    fun `on isFullName when is only first name should return false`() {
        assertEquals(false, "Koji".isFullName())
    }

    @Test
    fun `on isFullName when is given full name should return true`() {
        assertEquals(true, "Koji Osugi".isFullName())
    }
}
