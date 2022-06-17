package com.playground.transaction.compose.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.playground.domain.model.Country
import com.playground.domain.usecase.TransactionUseCases
import com.playground.test.infrastructure.MainDispatcherRule
import com.playground.transaction.compose.presentation.navigation.SendTransactionRoute
import io.mockk.coEvery
import io.mockk.mockk
import java.math.BigDecimal
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SendTransactionViewModelTest {

    @get:Rule
    val instantRuleExecutor = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SendTransactionViewModel

    private val transactionUseCases = mockk<TransactionUseCases>()

    @Before
    fun setup() {
        viewModel = SendTransactionViewModel(transactionUseCases)
    }

    @Test
    fun `on navigate then should set with given navigation`() {
        // arrange
        val route = SendTransactionRoute.INSERT_NAME

        // act
        viewModel.navigate(route)

        // assert
        assertEquals(route, viewModel.navigation.value)
    }

    @Test
    fun `on setSelectedCountry when given country is different than current country then should reset phone`() {
        // arrange
        val phoneNumber = "98225-2044"
        val firstSelectedCountry = Country.NIGERIA
        val lastSelectedCountry = Country.BRAZIL

        // act
        viewModel.setSelectedCountry(firstSelectedCountry)
        viewModel.phone.value = phoneNumber
        viewModel.setSelectedCountry(lastSelectedCountry)

        // assert
        assertEquals("", viewModel.phone.value)
    }

    @Test
    fun `on getFullPhoneNumber then should return formatted full phone number`() {
        // arrange
        val phoneNumber = "98225-2044"
        val selectedCountry = Country.BRAZIL

        // act
        viewModel.setSelectedCountry(selectedCountry)
        viewModel.phone.value = phoneNumber
        val result = viewModel.getFullPhoneNumber()

        // assert
        assertEquals("+55 98225-2044", result)
    }

    @Test
    fun `on getExchangeValue when request succeed then should return exchanged value and redirect to summary`() {
        // arrange
        val transferValue = BigDecimal(1).setScale(2) // equivalent to 1,00
        val selectedCountry = Country.BRAZIL
        coEvery {
            transactionUseCases.getExchangedValue(transferValue, "BRL")
        } returns BigDecimal(5)

        // act
        viewModel.transferValue.value = "US$ 1,00"
        viewModel.setSelectedCountry(selectedCountry)
        viewModel.getExchangeValue()

        // assert
        assertEquals("R$ 5,00", viewModel.exchangedMoney.value)
        assertEquals(SendTransactionRoute.SUMMARY, viewModel.navigation.value)
    }

    @Test
    fun `on getExchangeValue when request failure then should inform error message`() {
        // arrange
        val selectedCountry = Country.BRAZIL
        coEvery { transactionUseCases.getExchangedValue(any(), any()) } throws Exception()

        // act
        viewModel.transferValue.value = "US$ 1,00"
        viewModel.setSelectedCountry(selectedCountry)
        viewModel.getExchangeValue()

        // assert
        assertEquals(true, viewModel.uiState.value is SendTransactionViewModel.UIState.Error)
    }

    @Test
    fun `on sendTransaction when request succeed then should redirect to success and hide app bar`() {
        // arrange
        val transferValue = BigDecimal(1).setScale(2) // equivalent to 1,00
        val fullPhone = "+55 9999-6999"
        val selectedCountry = Country.BRAZIL
        coEvery {
            transactionUseCases.sendTransaction(transferValue, fullPhone, "BRL")
        } returns Unit

        // act
        viewModel.transferValue.value = "US$ 1,00"
        viewModel.setSelectedCountry(selectedCountry)
        viewModel.phone.value = "9999-6999"
        viewModel.sendTransaction()

        // assert
        assertEquals(true, viewModel.hideTopBar.value)
        assertEquals(SendTransactionRoute.SUCCESS, viewModel.navigation.value)
    }

    @Test
    fun `on sendTransaction when request fail then inform error message`() {
        // arrange
        val selectedCountry = Country.BRAZIL
        coEvery { transactionUseCases.sendTransaction(any(), any(), any()) } throws Exception()

        // act
        viewModel.transferValue.value = "US$ 1,00"
        viewModel.setSelectedCountry(selectedCountry)
        viewModel.phone.value = "9999-6999"
        viewModel.sendTransaction()

        // assert
        assertEquals(true, viewModel.uiState.value is SendTransactionViewModel.UIState.Error)
    }
}
