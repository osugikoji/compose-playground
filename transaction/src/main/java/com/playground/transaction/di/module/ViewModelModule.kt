package com.playground.transaction.di.module

import com.playground.transaction.presentation.sendtransaction.viewmodel.SendTransactionViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal class ViewModelModule {

    fun provide() = module {
        viewModel { SendTransactionViewModel(Dispatchers.Main, get()) }
    }
}
