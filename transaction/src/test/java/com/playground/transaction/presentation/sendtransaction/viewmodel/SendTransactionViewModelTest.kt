package com.playground.transaction.presentation.sendtransaction.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.playground.domain.model.Country
import com.playground.domain.usecase.TransactionUseCases
import com.playground.domain.R
import com.playground.transaction.presentation.sendtransaction.viewmodel.SendTransactionViewModel.UIState
import io.mockk.coEvery
import io.mockk.mockk
import java.math.BigDecimal
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SendTransactionViewModelTest {

    @get:Rule
    val instantRuleExecutor = InstantTaskExecutorRule()

    private lateinit var viewModel: SendTransactionViewModel

    private val transactionUseCases = mockk<TransactionUseCases>()

    @Before
    fun setup() {
        viewModel = SendTransactionViewModel(Dispatchers.Unconfined, transactionUseCases)
    }

    @Test
    fun `on setTransferValue then should set given value`() {
        // act
        viewModel.setTransferValue("US$ 100,00")

        // assert0
        assertEquals(BigDecimal(100).setScale(2), viewModel.dataHolder.transferValue)
    }

    @Test
    fun `on setTransferValue when given an invalid value then should disable button`() {
        // act
        viewModel.setTransferValue("US$ 0,00")

        // assert
        assertEquals(false, viewModel.enableButton.value)
    }

    @Test
    fun `on setTransferValue when given a valid value then should enable button`() {
        // act
        viewModel.setTransferValue("US$ 100,00")

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
        val transferValue = "US$ 100,00"
        coEvery {
            transactionUseCases.getExchangedValue(BigDecimal(100).setScale(2), "BRL")
        } throws Exception("Request failed")

        // act
        viewModel.setTransferValue(transferValue)
        viewModel.setSelectedCountry(Country.BRAZIL)
        viewModel.getTransactionExchangedValue()

        // assert
        val error = viewModel.uiState.value as UIState.Error
        assertEquals("Request failed", error.message)
    }

    @Test
    fun `on getExchangedValue when request succeed then inform success state and build summary model`() {
        // arrange
        val transferValue = "US$ 100,00"
        coEvery {
            transactionUseCases.getExchangedValue(BigDecimal(100).setScale(2), "BRL")
        } returns BigDecimal(500)

        // act
        viewModel.setTransferValue(transferValue)
        viewModel.setRecipientName("Lionel Messi")
        viewModel.setSelectedCountry(Country.BRAZIL)
        viewModel.setRecipientPhone("11 1111-1111")
        viewModel.getTransactionExchangedValue()

        // assert
        val isExpectedState = viewModel.uiState.value is UIState.TransactionExchangedSuccess
        assertEquals(true, isExpectedState)
        assertEquals("US$ 100,00", viewModel.summary.transferValue)
        assertEquals("Lionel Messi", viewModel.summary.recipientName)
        assertEquals(R.string.country_brazil, viewModel.summary.countryName)
        assertEquals(R.drawable.ic_brazil_flag, viewModel.summary.countryIcon)
        assertEquals("+55 11 1111-1111", viewModel.summary.fullPhoneNumber)
        assertEquals("R$ 500,00", viewModel.summary.exchangedValue)
    }

    @Test
    fun `on sendTransaction when request failed then inform error state`() {
        // arrange
        val transferValue = "US$ 100,00"
        val transferValueBigDecimal = BigDecimal(100).setScale(2)
        val fullPhoneNumber = "+55 11 1111-1111"
        val currency = "BRL"
        coEvery {
            transactionUseCases.sendTransaction(transferValueBigDecimal, fullPhoneNumber, currency)
        } throws Exception("Request failed")

        // act
        viewModel.setTransferValue(transferValue)
        viewModel.setSelectedCountry(Country.BRAZIL)
        viewModel.setRecipientPhone("11 1111-1111")
        viewModel.sendTransaction()

        // assert
        val error = viewModel.uiState.value as UIState.Error
        assertEquals("Request failed", error.message)
    }

    @Test
    fun `on sendTransaction when request succeed then inform send transaction success state`() {
        // arrange
        val transferValue = "US$ 100,00"
        val transferValueBigDecimal = BigDecimal(100).setScale(2)
        val fullPhoneNumber = "+55 11 1111-1111"
        val currency = "BRL"
        coEvery {
            transactionUseCases.sendTransaction(transferValueBigDecimal, fullPhoneNumber, currency)
        } returns Unit

        // act
        viewModel.setTransferValue(transferValue)
        viewModel.setSelectedCountry(Country.BRAZIL)
        viewModel.setRecipientPhone("11 1111-1111")
        viewModel.sendTransaction()

        // assert
        val isExpectedState = viewModel.uiState.value is UIState.SendTransactionSuccess
        assertEquals(true, isExpectedState)
    }
}
