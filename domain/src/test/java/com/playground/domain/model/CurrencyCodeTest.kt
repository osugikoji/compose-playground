package com.playground.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyCodeTest {

    @Test
    fun `check all currency code values`() {
        assertEquals("USD", CurrencyCode.UNITED_STATES)
        assertEquals("BRL", CurrencyCode.BRAZIL)
        assertEquals("KES", CurrencyCode.KENYA)
        assertEquals("NGN", CurrencyCode.NIGERIA)
        assertEquals("TZS", CurrencyCode.TANZANIA)
        assertEquals("UGX", CurrencyCode.UGANDA)
    }
}
