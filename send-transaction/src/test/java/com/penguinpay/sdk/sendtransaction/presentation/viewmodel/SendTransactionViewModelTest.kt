package com.penguinpay.sdk.sendtransaction.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.penguinpay.sdk.sendtransaction.domain.business.TransactionBusiness
import com.penguinpay.sdk.sendtransaction.domain.model.Country
import com.penguinpay.sdk.sendtransaction.presentation.viewmodel.SendTransactionViewModel.UIState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SendTransactionViewModelTest {

    @get:Rule
    val instantRuleExecutor = InstantTaskExecutorRule()

    private lateinit var viewModel: SendTransactionViewModel

    private val transactionBusiness = mockk<TransactionBusiness>()

    @Before
    fun setup() {
        viewModel = SendTransactionViewModel(Dispatchers.Unconfined, transactionBusiness)
    }

    @Test
    fun `on setTransferValue then should set given value`() {
        // act
        viewModel.setTransferValue("1001")

        // assert
        assertEquals("1001", viewModel.dataHolder.transferValue)
    }

    @Test
    fun `on setTransferValue when given an invalid binary value then should disable button`() {
        // act
        viewModel.setTransferValue("10015")

        // assert
        assertEquals(false, viewModel.enableButton.value)
    }

    @Test
    fun `on setTransferValue when given a valid binary value then should enable button`() {
        // act
        viewModel.setTransferValue("1001")

        // assert
        assertEquals(true, viewModel.enableButton.value)
    }

    @Test
    fun `on setRecipientName then should set given value`() {
        // act
        viewModel.setRecipientName("Andre Jr")

        // assert
        assertEquals("Andre Jr", viewModel.dataHolder.recipientName)
    }

    @Test
    fun `on setRecipientName when given an invalid full name then should disable button`() {
        // act
        viewModel.setRecipientName("Koji")

        // assert
        assertEquals(false, viewModel.enableButton.value)
    }

    @Test
    fun `on setRecipientName when given an valid full name then should enable button`() {
        // act
        viewModel.setRecipientName("Koji Osugi")

        // assert
        assertEquals(true, viewModel.enableButton.value)
    }

    @Test
    fun `on setSelectedCountry then should set given value`() {
        // act
        viewModel.setSelectedCountry(Country.KENYA)

        // assert
        assertEquals(Country.KENYA, viewModel.dataHolder.selectedCountry)
    }

    @Test
    fun `on setSelectedCountry when has given an invalid phone number then should disable button`() {
        // act
        viewModel.setRecipientPhone("19982")
        viewModel.setSelectedCountry(Country.KENYA)

        // assert
        assertEquals(false, viewModel.enableButton.value)
    }

    @Test
    fun `on setSelectedCountry when has given a valid phone number then should enable button`() {
        // act
        viewModel.setSelectedCountry(Country.KENYA)
        viewModel.setRecipientPhone("98225-2031")
        viewModel.setSelectedCountry(Country.KENYA)

        // assert
        assertEquals(true, viewModel.enableButton.value)
    }

    @Test
    fun `on setSelectedCountry when selected another country then should clear previous phone inputted`() {
        // act
        viewModel.setSelectedCountry(Country.KENYA)
        viewModel.setRecipientPhone("98225-2031")
        viewModel.setSelectedCountry(Country.NIGERIA)

        // assert
        assertEquals("", viewModel.dataHolder.phoneNumber)
    }

    @Test
    fun `on setRecipientPhone then should set given value`() {
        // act
        viewModel.setRecipientPhone("982252031")

        // assert
        assertEquals("982252031", viewModel.dataHolder.phoneNumber)
    }

    @Test
    fun `on setRecipientPhone when given an invalid phone number then should disable button`() {
        // act
        viewModel.setSelectedCountry(Country.NIGERIA)
        viewModel.setRecipientPhone("9822520311")

        // assert
        assertEquals(false, viewModel.enableButton.value)
    }

    @Test
    fun `on setRecipientName when given a valid phone number then should enable button`() {
        // act
        viewModel.setSelectedCountry(Country.NIGERIA)
        viewModel.setRecipientPhone("9822520")

        // assert
        assertEquals(true, viewModel.enableButton.value)
    }

    @Test
    fun `on getTransactionExchangedValue when request failed then inform error state`() {
        // arrange
        every { transactionBusiness.getTransactionExchangedValue("0110", Country.NIGERIA) } returns
                flow { throw Exception("Request failed") }

        // act
        viewModel.setTransferValue("0110")
        viewModel.setSelectedCountry(Country.NIGERIA)
        viewModel.getTransactionExchangedValue()

        // assert
        val error = viewModel.uiState.value as UIState.Error
        assertEquals("Request failed", error.message)
    }

    @Test
    fun `on getTransactionExchangedValue when request succeed then inform transaction exchanged success state and set exchanged value`() {
        // arrange
        every { transactionBusiness.getTransactionExchangedValue("0110", Country.NIGERIA) } returns
                flowOf("01100")

        // act
        viewModel.setTransferValue("0110")
        viewModel.setSelectedCountry(Country.NIGERIA)
        viewModel.getTransactionExchangedValue()

        // assert
        val isExpectedState = viewModel.uiState.value is UIState.TransactionExchangedSuccess
        assertEquals(true, isExpectedState)
        assertEquals("01100", viewModel.dataHolder.exchangedValue)
    }

    @Test
    fun `on sendTransaction when request failed then inform error state`() {
        // arrange
        every { transactionBusiness.sendTransaction(any()) } returns
                flow { throw Exception("Request failed") }

        // act
        viewModel.sendTransaction()

        // assert
        val error = viewModel.uiState.value as UIState.Error
        assertEquals("Request failed", error.message)
    }

    @Test
    fun `on sendTransaction when request succeed then inform send transaction success state`() {
        // arrange
        every { transactionBusiness.sendTransaction(any()) } returns flowOf(Unit)

        // act
        viewModel.sendTransaction()

        // assert
        val isExpectedState = viewModel.uiState.value is UIState.SendTransactionSuccess
        assertEquals(true, isExpectedState)
    }
}