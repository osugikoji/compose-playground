package com.playground.transaction.compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.playground.transaction.compose.R
import com.playground.transaction.compose.presentation.screen.InsertRecipientPhoneScreen
import com.playground.transaction.compose.presentation.screen.InsertTransferNameScreen
import com.playground.transaction.compose.presentation.screen.InsertTransferValueScreen
import com.playground.transaction.compose.presentation.screen.SelectCountryScreen
import com.playground.transaction.compose.presentation.screen.SendTransactionSummaryScreen
import com.playground.transaction.compose.presentation.viewmodel.SendTransactionViewModel
import com.playground.transaction.compose.ui.components.message.SuccessMessageScreen

@Composable
internal fun SendTransactionNavigation(
    viewModel: SendTransactionViewModel,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit = {},
) {
    NavHost(
        navController = navHostController,
        startDestination = SendTransactionRoute.INSERT_VALUE.name
    ) {
        composable(SendTransactionRoute.INSERT_VALUE.name) {
            InsertTransferValueScreen(
                transferValueState = viewModel.transferValue,
                modifier = modifier,
                onNextAction = { viewModel.navigate(SendTransactionRoute.INSERT_NAME) }
            )
        }
        composable(SendTransactionRoute.INSERT_NAME.name) {
            InsertTransferNameScreen(
                transferValue = viewModel.transferValue.value,
                nameState = viewModel.name,
                modifier = modifier,
                onNextAction = { viewModel.navigate(SendTransactionRoute.SELECT_COUNTRY) }
            )
        }
        composable(SendTransactionRoute.SELECT_COUNTRY.name) {
            SelectCountryScreen(
                onSelectedCountry = {
                    viewModel.setSelectedCountry(it)
                    viewModel.navigate(SendTransactionRoute.INSERT_PHONE)
                },
                modifier = modifier
            )
        }
        composable(SendTransactionRoute.INSERT_PHONE.name) {
            InsertRecipientPhoneScreen(
                phoneState = viewModel.phone,
                phoneCountry = viewModel.selectedCountry,
                modifier = modifier,
                onNextAction = { viewModel.getExchangeValue() }
            )
        }
        composable(SendTransactionRoute.SUMMARY.name) {
            SendTransactionSummaryScreen(
                transferMoney = viewModel.transferValue.value,
                modifier = modifier,
                recipientName = viewModel.name.value,
                country = viewModel.selectedCountry,
                fullPhoneNumber = viewModel.getFullPhoneNumber(),
                transferExchangeValue = viewModel.exchangedMoney.value,
                onTransferAction = { viewModel.sendTransaction() },
            )
        }
        composable(SendTransactionRoute.SUCCESS.name) {
            val transferMoney = viewModel.transferValue.value
            SuccessMessageScreen(
                title = stringResource(id = R.string.transaction_success_title),
                message = stringResource(id = R.string.transaction_success_message, transferMoney),
                buttonAction = onFinish
            )
        }
    }
}
