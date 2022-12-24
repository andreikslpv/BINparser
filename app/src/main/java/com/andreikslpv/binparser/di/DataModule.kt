package com.andreikslpv.binparser.di

import com.andreikslpv.binparser.data.HistoryRepositoryImpl
import com.andreikslpv.binparser.domain.HistoryRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import java.io.File

const val NAME_OF_LOCAL_STORAGE = "local.json"

val dataModule = module {

    single<HistoryRepository> {
        HistoryRepositoryImpl(File("${androidApplication().filesDir}/$NAME_OF_LOCAL_STORAGE"))
    }

}