package com.playground.transaction

import com.playground.transaction.core.currencySymbolToBigDecimal
import com.playground.transaction.core.toCurrencySymbol
import java.math.BigDecimal
import java.util.Locale
import org.junit.Assert.assertEquals
import org.junit.Test

class MoneyExtensionsTest {

    @Test
    fun `on toCurrencySymbol when not inform any parameter should return US dollar monetary format`() {
        // arrange
        val valueToFormat = BigDecimal(100)

        // act
        val result = valueToFormat.toCurrencySymbol()

        // assert
        assertEquals("US$ 100,00", result)
    }

    @Test
    fun `on toCurrencySymbol when value is zero with two scale then should format correctly`() {
        // arrange
        val valueToFormat = BigDecimal(0).setScale(2)

        // act
        val result = valueToFormat.toCurrencySymbol()

        // assert
        assertEquals("US$ 0,00", result)
    }

    @Test
    fun `on toCurrencySymbol when pass currencySymbol parameter then should format correctly`() {
        // arrange
        val valueToFormat = BigDecimal(100)

        // act
        val result = valueToFormat.toCurrencySymbol("BRL")

        // assert
        assertEquals("R$ 100,00", result)
    }

    @Test
    fun `on currencySymbolToBigDecimal when pass currencySymbol parameter then should format correctly`() {
        // arrange
        val valueToFormat = BigDecimal(100)

        // act
        val result = valueToFormat.toCurrencySymbol("BRL")

        // assert
        assertEquals("R$ 100,00", result)
    }

    @Test
    fun `on currencySymbolToBigDecimal when given value has different currency of informed locale then return zero`() {
        // arrange
        val valueToFormat = "R$ 100,00"

        // act
        val result = valueToFormat.currencySymbolToBigDecimal(Locale.US)

        // assert
        assertEquals(BigDecimal.ZERO, result)
    }

    @Test
    fun `on currencySymbolToBigDecimal when inform correct value then should convert correctly`() {
        // arrange
        val valueToFormat = "$ 100,00"

        // act
        val result = valueToFormat.currencySymbolToBigDecimal(Locale.US)

        // assert
        assertEquals(BigDecimal(100).setScale(2), result)
    }
}
