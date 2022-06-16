package com.playground.transaction.compose.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.playground.transaction.compose.di.component.CustomKoinComponent
import com.playground.transaction.compose.presentation.navigation.SendTransactionNavigation
import com.playground.transaction.compose.presentation.navigation.SendTransactionRoute
import com.playground.transaction.compose.presentation.viewmodel.SendTransactionViewModel
import com.playground.transaction.compose.presentation.viewmodel.SendTransactionViewModel.UIState
import com.playground.transaction.compose.ui.components.loading.LoadingScreen
import com.playground.transaction.compose.ui.components.message.ErrorMessageBar
import com.playground.transaction.compose.ui.components.navigation.StandardAppBar
import com.playground.transaction.compose.ui.theme.PlaygroundTheme
import com.playground.transaction.compose.ui.theme.Spacing
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SendTransactionActivity : AppCompatActivity(), CustomKoinComponent {

    private val viewModel by viewModel<SendTransactionViewModel>()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content() }
        viewModel.navigation.observe(this) { handleNavigation(it) }
    }

    @Composable
    private fun Content() {
        val navHostController = rememberNavController()
        navController = navHostController
        PlaygroundTheme {
            SendTransactionScreen(
                viewModel = viewModel,
                navHostController = navHostController,
                onBackAction = { onBackPressed() },
                onExitAction = { finish() },
                onFinish = { finish() }
            )
        }
    }

    private fun handleNavigation(route: SendTransactionRoute) {
        when (route) {
            SendTransactionRoute.SUCCESS -> {
                navController.navigate(route.name) {
                    popUpTo(SendTransactionRoute.INSERT_VALUE.name) { inclusive = true }
                }
            }
            else -> navController.navigate(route.name)
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SendTransactionActivity::class.java)
    }
}

@Composable
private fun SendTransactionScreen(
    viewModel: SendTransactionViewModel,
    navHostController: NavHostController,
    onBackAction: () -> Unit = {},
    onExitAction: () -> Unit = {},
    onFinish: () -> Unit = {},
) {
    val uiState = viewModel.uiState.collectAsState().value
    val shouldHideAppBar = viewModel.hideTopBar.collectAsState().value
    Column {
        TopAppBar(shouldHideAppBar, onBackAction, onExitAction)
        NavigationSection(viewModel, navHostController, onFinish)
    }
    States {
        when (uiState) {
            is UIState.Loading -> LoadingState()
            is UIState.Error -> ErrorState(uiState.message)
            else -> Unit
        }
    }
}

@Composable
private fun TopAppBar(
    shouldHideAppBar: Boolean = false,
    onBackAction: () -> Unit = {},
    onExitAction: () -> Unit = {},
) {
    AnimatedVisibility(
        visible = !shouldHideAppBar,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            StandardAppBar(
                title = "Send money",
                startIcon = Icons.Filled.ArrowBack,
                endIcon = Icons.Filled.Close,
                onStartIconAction = onBackAction,
                onEndIconAction = onExitAction
            )
        }
    )
}

@Composable
private fun NavigationSection(
    viewModel: SendTransactionViewModel,
    navHostController: NavHostController,
    onFinish: () -> Unit = {},
) {
    SendTransactionNavigation(
        viewModel = viewModel,
        navHostController = navHostController,
        modifier = Modifier
            .padding(horizontal = Spacing.MD)
            .padding(top = Spacing.BG, bottom = Spacing.XM),
        onFinish = onFinish,
    )
}

@Composable
private inline fun States(content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) { content() }
}

@Composable
private fun LoadingState() {
    val focusManager = LocalFocusManager.current
    LoadingScreen()
    focusManager.clearFocus()
}

@Composable
private fun BoxScope.ErrorState(errorMessage: String) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    ErrorMessageBar(
        snackBarHostState = snackBarHostState,
        modifier = Modifier.align(Alignment.BottomCenter)
    )
    scope.launch { snackBarHostState.showSnackbar(errorMessage) }
}

@Preview(showBackground = true)
@Composable
private fun SendTransactionPreview() {

}
