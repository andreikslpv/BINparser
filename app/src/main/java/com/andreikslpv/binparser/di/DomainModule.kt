package com.andreikslpv.binparser.di

import com.andreikslpv.binparser.domain.usecase.AddRequestToHistoryUseCase
import com.andreikslpv.binparser.domain.usecase.GetHistoryUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetHistoryUseCase> {
        GetHistoryUseCase(historyRepository = get())
    }

    factory<AddRequestToHistoryUseCase> {
        AddRequestToHistoryUseCase(historyRepository = get())
    }
}