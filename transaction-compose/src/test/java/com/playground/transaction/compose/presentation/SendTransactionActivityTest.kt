package com.playground.transaction.compose.presentation

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import com.playground.core.idling.EspressoIdlingResource
import com.playground.test.utils.FileReader
import com.playground.transaction.compose.di.TransactionComposeInitializer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

@Config(manifest = Config.NONE, qualifiers = "pt-rBR")
@RunWith(value = RobolectricTestRunner::class)
class SendTransactionActivityTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private lateinit var mockWebServer: MockWebServer

    @get:Rule
    internal val composeTestRule = createAndroidComposeRule<SendTransactionActivity>()

    init {
        TransactionComposeInitializer.start(context, "123", "http://localhost:8607/")
    }

    @Before
    fun setup() {
        ShadowLog.stream = System.out
        mockWebServer = MockWebServer()
        mockWebServer.start(8607)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        TransactionComposeInitializer.stop()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun `verify transaction value input field is formatting to monetary value`() {
        // arrange
        val transactionValue = "100" // Equivalent to 1,00
        val robot = SendTransactionRobot(composeTestRule)

        // act
        robot.insertTransactionValue(transactionValue)

        // assert
        robot.verifyTransactionValueTextField("US$ 1,00")
    }

    @Test
    fun `verify if the inserted transaction value is been converted to the given selected country currency`() {
        // arrange
        val transactionValue = "100" // Equivalent to 1,00
        val recipientFullName = "Neymar Junior"
        val selectedCountry = "Brazil"
        val fullPhoneNumber = "922545889"
        val exchangeJson = FileReader.read("raw/exchange_200.json")
        val response = buildMockResponse(exchangeJson)
        val robot = SendTransactionRobot(composeTestRule)
        mockWebServer.enqueue(response)

        // act
        robot
            .insertTransactionValueAndProceed(transactionValue)
            .insertRecipientNameAndProceed(recipientFullName)
            .selectCountry(selectedCountry)
            .insertRecipientPhoneAndProceed(fullPhoneNumber)

        // assert
        robot.verifyTransferExchangedValue("R$ 5,00")
    }

    @Test
    fun `on happy flow verify has been redirected to success screen`() {
        // arrange
        val transactionValue = "100" // Equivalent to 1,00
        val recipientFullName = "Neymar Junior"
        val selectedCountry = "Brazil"
        val fullPhoneNumber = "922545889"
        val exchangeJson = FileReader.read("raw/exchange_200.json")
        val response = buildMockResponse(exchangeJson)
        val robot = SendTransactionRobot(composeTestRule)
        mockWebServer.enqueue(response)

        // act
        robot
            .insertTransactionValueAndProceed(transactionValue)
            .insertRecipientNameAndProceed(recipientFullName)
            .selectCountry(selectedCountry)
            .insertRecipientPhoneAndProceed(fullPhoneNumber)
            .transferClick()

        // assert
        robot.verifySuccessScreenMessage("US$ 1,00")
    }

    private fun buildMockResponse(body: String, code: Int = 200): MockResponse {
        return MockResponse()
            .setBody(body)
            .setResponseCode(code)
    }
}
