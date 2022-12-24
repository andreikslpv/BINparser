package com.andreikslpv.binparser.di

import com.andreikslpv.binparser.presentation.vm.HistoryViewModel
import com.andreikslpv.binparser.presentation.vm.RequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<HistoryViewModel> {
        HistoryViewModel(getHistoryUseCase = get())
    }

    viewModel<RequestViewModel> {
        RequestViewModel()
    }
}