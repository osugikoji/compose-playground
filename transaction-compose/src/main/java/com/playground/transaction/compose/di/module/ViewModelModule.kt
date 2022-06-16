package com.playground.transaction.compose.di.module

import com.playground.transaction.compose.presentation.viewmodel.SendTransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal class ViewModelModule {

    fun provide() = module {
        viewModel { SendTransactionViewModel(get()) }
    }
}
