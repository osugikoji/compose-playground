package com.playground.domain.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(value = RobolectricTestRunner::class)
class CountryTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun `check BRAZIL country properties is correctly`() {
        // act
        val result = Country.BRAZIL

        // assert
        assertEquals("Brazil", context.getString(result.countryName))
        assertEquals("+55", result.phonePrefix)
        assertEquals(9, result.phoneNumberLength)
        assertEquals("BRL", result.currencySymbol)
    }

    @Test
    fun `check KENYA country properties is correctly`() {
        // act
        val result = Country.KENYA

        // assert
        assertEquals("Kenya", context.getString(result.countryName))
        assertEquals("+254", result.phonePrefix)
        assertEquals(9, result.phoneNumberLength)
        assertEquals("KES", result.currencySymbol)
    }

    @Test
    fun `check NIGERIA country properties is correctly`() {
        // act
        val result = Country.NIGERIA

        // assert
        assertEquals("Nigeria", context.getString(result.countryName))
        assertEquals("+234", result.phonePrefix)
        assertEquals(7, result.phoneNumberLength)
        assertEquals("NGN", result.currencySymbol)
    }

    @Test
    fun `check TANZANIA country properties is correctly`() {
        // act
        val result = Country.TANZANIA

        // assert
        assertEquals("Tanzania", context.getString(result.countryName))
        assertEquals("+255", result.phonePrefix)
        assertEquals(9, result.phoneNumberLength)
        assertEquals("TZS", result.currencySymbol)
    }

    @Test
    fun `check UGANDA country properties is correctly`() {
        // act
        val result = Country.UGANDA

        // assert
        assertEquals("Uganda", context.getString(result.countryName))
        assertEquals("+256", result.phonePrefix)
        assertEquals(7, result.phoneNumberLength)
        assertEquals("UGX", result.currencySymbol)
    }
}
