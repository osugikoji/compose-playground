package com.playground.transaction.compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.playground.transaction.compose.presentation.screen.InsertTransferNameScreen
import com.playground.transaction.compose.presentation.screen.InsertTransferValueScreen
import com.playground.transaction.compose.presentation.viewmodel.SendTransactionViewModel

internal enum class SendTransactionRoute {
    INSERT_VALUE,
    INSERT_NAME,
    SELECT_COUNTRY,
    INSERT_PHONE,
    SUMMARY
}

@Composable
internal fun SendTransactionNavigation(
    viewModel: SendTransactionViewModel,
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = SendTransactionRoute.INSERT_VALUE.name
    ) {
        composable(SendTransactionRoute.INSERT_VALUE.name) {
            InsertTransferValueScreen(
                valueInput = viewModel.transactionValue.collectAsState(),
                onValueInputChanged = viewModel::setTransferValue
            )
        }
        composable(SendTransactionRoute.INSERT_NAME.name) {
            InsertTransferNameScreen(
                transferValue = viewModel.transactionValue.value,
                name = viewModel.insertTransferName.collectAsState(),
                onValueInputChanged = viewModel::setInsertTransferName
            )
        }
    }
}
