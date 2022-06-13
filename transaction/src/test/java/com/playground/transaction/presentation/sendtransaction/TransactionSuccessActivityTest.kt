package com.playground.transaction.presentation.sendtransaction

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.playground.test.espresso.EspressoAssertion
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE, qualifiers = "pt-rBR")
@RunWith(value = RobolectricTestRunner::class)
class TransactionSuccessActivityTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private var activityScenario: ActivityScenario<TransactionSuccessActivity>? = null

    @After
    fun tearDown() {
        activityScenario?.close()
    }

    @Test
    fun `when inform currency value then should should success message as expected`() {
        // arrange
        val currencyValue = "R$ 100,00"
        val intent = TransactionSuccessActivity.getIntent(context, currencyValue)

        // act
        activityScenario = ActivityScenario.launch(intent)

        // assert
        val expectedText = "The transfer in the amount of $currencyValue was sent successfully."
        EspressoAssertion.isVisible(expectedText)
    }

    @Test(expected = Exception::class)
    fun `when not inform currency value then should throw an exception`() {
        // act
        activityScenario = ActivityScenario.launch(TransactionSuccessActivity::class.java)
    }
}
