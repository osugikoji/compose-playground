package com.playground.transaction.presentation.sendtransaction

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.playground.core.idling.EspressoIdlingResource
import com.playground.test.utils.FileReader
import com.playground.transaction.di.TransactionInitializer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE, qualifiers = "pt-rBR")
@RunWith(value = RobolectricTestRunner::class)
class SendTransactionActivityTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private lateinit var activityScenario: ActivityScenario<SendTransactionActivity>

    private lateinit var intent: Intent

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        Intents.init()
        TransactionInitializer.start(context, "123", "http://localhost:8607/")
        intent = Intent(context, SendTransactionActivity::class.java)
        mockWebServer = MockWebServer()
        mockWebServer.start(8607)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        Intents.release()
        mockWebServer.shutdown()
        activityScenario.close()
        TransactionInitializer.stop()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun `verify transaction value input field is formatting to monetary value`() {
        // arrange
        val transactionValue = "100" // Equivalent to 1,00

        // act
        launchActivity()
        SendTransactionRobot.insertTransactionValue(transactionValue)

        // assert
        SendTransactionRobot.verifyTransactionValueEditText("US$ 1,00")
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
        mockWebServer.enqueue(response)

        // act
        launchActivity()
        SendTransactionRobot
            .insertTransactionValueAndProceed(transactionValue)
            .insertRecipientNameAndProceed(recipientFullName)
            .selectCountry(selectedCountry)
            .insertRecipientPhoneAndProceed(fullPhoneNumber)

        // assert
        SendTransactionRobot.verifyTransferExchangedValue("R$ 5,00")
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
        mockWebServer.enqueue(response)

        // act
        launchActivity()
        SendTransactionRobot
            .insertTransactionValueAndProceed(transactionValue)
            .insertRecipientNameAndProceed(recipientFullName)
            .selectCountry(selectedCountry)
            .insertRecipientPhoneAndProceed(fullPhoneNumber)
            .proceed()

        // assert
        intended(hasComponent(TransactionSuccessActivity::class.java.name))
    }

    @Test
    fun `when exchange request failed then should show error message`() {
        // arrange
        val transactionValue = "100" // Equivalent to 1,00
        val recipientFullName = "Neymar Junior"
        val selectedCountry = "Brazil"
        val fullPhoneNumber = "922545889"
        val exchangeJson = FileReader.read("raw/exchange_400.json")
        val response = buildMockResponse(exchangeJson, 400)
        mockWebServer.enqueue(response)

        // act
        launchActivity()
        SendTransactionRobot
            .insertTransactionValueAndProceed(transactionValue)
            .insertRecipientNameAndProceed(recipientFullName)
            .selectCountry(selectedCountry)
            .insertRecipientPhoneAndProceed(fullPhoneNumber)

        // assert
        SendTransactionRobot.verifyErrorMessageHasBeenShown()
    }

    private fun launchActivity() {
        activityScenario = ActivityScenario.launch(intent)
    }

    private fun buildMockResponse(body: String, code: Int = 200): MockResponse {
        return MockResponse()
            .setBody(body)
            .setResponseCode(code)
    }
}
