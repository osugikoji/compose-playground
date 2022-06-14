package com.playground.transaction.compose.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.playground.transaction.compose.di.component.CustomKoinComponent
import com.playground.transaction.compose.presentation.navigation.SendTransactionNavigation
import com.playground.transaction.compose.presentation.navigation.SendTransactionRoute
import com.playground.transaction.compose.presentation.viewmodel.SendTransactionViewModel
import com.playground.transaction.compose.ui.components.StandardAppBar
import com.playground.transaction.compose.ui.components.StandardButton
import com.playground.transaction.compose.ui.theme.PlaygroundTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SendTransactionActivity : AppCompatActivity(), CustomKoinComponent {

    private val viewModel by viewModel<SendTransactionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme {
                SendTransactionScreen(
                    viewModel = viewModel,
                    onBackAction = { onBackPressed() },
                    onExitAction = { finish() },
                    onNextAction = ::handleNextButton
                )
            }
        }
    }

    private fun handleNextButton(navController: NavController) {
        when (navController.currentDestination?.route) {
            SendTransactionRoute.INSERT_VALUE.name -> {
                navController.navigate(SendTransactionRoute.INSERT_NAME.name)
            }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SendTransactionActivity::class.java)
    }
}

@Composable
private fun SendTransactionScreen(
    viewModel: SendTransactionViewModel,
    onBackAction: () -> Unit = {},
    onExitAction: () -> Unit = {},
    onNextAction: (NavController) -> Unit = {},
) {
    val navHostController = rememberNavController()
    Column {
        StandardAppBar(
            title = "Send money",
            startIcon = Icons.Filled.ArrowBack,
            endIcon = Icons.Filled.Close,
            onStartIconAction = onBackAction,
            onEndIconAction = onExitAction
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            SendTransactionNavigation(
                navHostController = navHostController,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.weight(1f))
            StandardButton(
                text = "Next",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                enabled = viewModel.enableButton.collectAsState().value,
                onClick = { onNextAction(navHostController) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SendTransactionPreview() {

}
