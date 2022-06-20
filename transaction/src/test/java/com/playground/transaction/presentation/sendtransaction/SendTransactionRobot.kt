package com.playground.transaction.presentation.sendtransaction

import com.playground.test.espresso.EspressoAction
import com.playground.test.espresso.EspressoAssertion
import com.playground.test.utils.retryer
import com.playground.transaction.R

object SendTransactionRobot {

    fun insertTransactionValue(transactionValue: String): SendTransactionRobot {
        EspressoAction.typeOnEditText(R.id.edit_text_transaction_value, transactionValue)
        return this
    }

    fun insertTransactionValueAndProceed(transactionValue: String): SendTransactionRobot {
        EspressoAction.typeOnEditText(R.id.edit_text_transaction_value, transactionValue)
        proceed()
        return this
    }

    fun insertRecipientNameAndProceed(recipientFullName: String): SendTransactionRobot {
        EspressoAction.typeOnEditText(R.id.edit_text_name, recipientFullName)
        proceed()
        return this
    }

    fun selectCountry(selectedCountry: String): SendTransactionRobot {
        EspressoAction.clickOn(selectedCountry)
        return this
    }

    fun insertRecipientPhoneAndProceed(fullPhoneNumber: String): SendTransactionRobot {
        EspressoAction.typeOnEditText(R.id.edit_text_phone, fullPhoneNumber)
        proceed()
        return this
    }

    fun proceed(): SendTransactionRobot {
        EspressoAction.clickOn(R.id.button_navigation)
        return this
    }

    fun verifyTransferExchangedValue(exchangedValue: String): SendTransactionRobot {
        EspressoAssertion.checkText(R.id.text_view_transfer_exchange_value, exchangedValue)
        return this
    }

    fun verifyErrorMessageHasBeenShown(): SendTransactionRobot {
        val expectedText = "This mock exchange data error behaviour."
        retryer { EspressoAssertion.isVisible(expectedText) }
        return this
    }

    fun verifyTransactionValueEditText(value: String): SendTransactionRobot {
        EspressoAssertion.checkText(R.id.edit_text_transaction_value, value)
        return this
    }
}
