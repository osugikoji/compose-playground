package com.playground.core.extensions

import java.math.BigDecimal
import java.util.Locale
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MoneyExtensionsTest {

    @Before
    fun setup() {
        val locale = Locale.ENGLISH
        Locale.setDefault(locale)
    }

    @Test
    fun `on toCurrencyFormat when value is empty then should return zero currency value`() {
        // arrange
        val valueToFormat = ""

        // act
        val result = valueToFormat.toCurrencyFormat("BRL")

        // assert
        assertEquals("R$ 0.00", result)
    }

    @Test
    fun `on toCurrencyFormat when is string value then should return formatted currency value`() {
        // arrange
        val valueToFormat = "100"

        // act
        val result = valueToFormat.toCurrencyFormat("BRL")

        // assert
        assertEquals("R$ 1.00", result)
    }

    @Test
    fun `on toCurrencyFormat when value is zero with two scale then should format correctly`() {
        // arrange
        val valueToFormat = BigDecimal(0).setScale(2)

        // act
        val result = valueToFormat.toCurrencyFormat("USD")

        // assert
        assertEquals("$ 0,00", result)
    }

    @Test
    fun `on toCurrencyFormat when pass brazil currency code then should format correctly`() {
        // arrange
        val valueToFormat = BigDecimal(100)

        // act
        val result = valueToFormat.toCurrencyFormat("BRL")

        // assert
        assertEquals("R$ 100.00", result)
    }

    @Test
    fun `on currencyFormatToBigDecimal when given value has different currency of informed locale then return zero`() {
        // arrange
        val valueToFormat = "R$ 100.00"

        // act
        val result = valueToFormat.currencyFormatToBigDecimal("USD")

        // assert
        assertEquals(BigDecimal.ZERO, result)
    }

    @Test
    fun `on currencyFormatToBigDecimal when inform correct value then should convert correctly`() {
        // arrange
        val valueToFormat = "$ 100.00"

        // act
        val result = valueToFormat.currencyFormatToBigDecimal("USD")

        // assert
        assertEquals(BigDecimal(100).setScale(2), result)
    }

    @Test
    fun `on hasMoney when value is greater than 0 then should return true`() {
        // arrange
        val valueToFormat = "$ 0.01"

        // act
        val result = valueToFormat.hasMoney()

        // assert
        assertEquals(true, result)
    }

    @Test
    fun `on hasMoney when value is 0 then should return false`() {
        // arrange
        val valueToFormat = "$ 0.00"

        // act
        val result = valueToFormat.hasMoney()

        // assert
        assertEquals(false, result)
    }
}
