package com.playground.transaction.compose.presentation

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput

internal class SendTransactionRobot(
    private val interactionProvider: SemanticsNodeInteractionsProvider,
) {

    fun insertTransactionValue(transactionValue: String): SendTransactionRobot {
        interactionProvider
            .onNodeWithContentDescription("Enter a dollar value")
            .performTextInput(transactionValue)
        return this
    }

    fun insertTransactionValueAndProceed(transactionValue: String): SendTransactionRobot {
        insertTransactionValue(transactionValue)
        proceed()
        return this
    }

    fun insertRecipientNameAndProceed(recipientFullName: String): SendTransactionRobot {
        interactionProvider
            .onNodeWithContentDescription("Enter the recipient full name")
            .performTextInput(recipientFullName)
        proceed()
        return this
    }

    fun selectCountry(selectedCountry: String): SendTransactionRobot {
        interactionProvider
            .onNodeWithText(selectedCountry)
            .performClick()
        return this
    }

    fun insertRecipientPhoneAndProceed(fullPhoneNumber: String): SendTransactionRobot {
        interactionProvider
            .onNodeWithContentDescription("Enter the recipient phone number")
            .performTextInput(fullPhoneNumber)
        proceed()
        return this
    }

    fun transferClick(): SendTransactionRobot {
        interactionProvider
            .onNodeWithText("Transfer")
            .performScrollTo()
            .assertIsDisplayed()
            .performClick()
        return this
    }

    fun verifyTransferExchangedValue(exchangedValue: String): SendTransactionRobot {
        interactionProvider
            .onNodeWithText(exchangedValue)
            .assertExists()
        return this
    }

    fun verifyTransactionValueTextField(value: String): SendTransactionRobot {
        interactionProvider
            .onNodeWithContentDescription("Enter a dollar value")
            .assert(hasText(value))
        return this
    }

    fun verifySuccessScreenMessage(value: String): SendTransactionRobot {
        val expectedMessage = "The transfer in the amount of $value was sent successfully."
        interactionProvider
            .onNodeWithText(expectedMessage)
            .assertIsDisplayed()
        return this
    }

    private fun proceed(): SendTransactionRobot {
        interactionProvider
            .onNodeWithText("Next")
            .performClick()
        return this
    }
}
