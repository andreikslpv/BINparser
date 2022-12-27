package com.andreikslpv.binparser.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreikslpv.binparser.domain.models.RequestDomainModel
import com.andreikslpv.binparser.domain.usecase.GetHistoryUseCase

class HistoryViewModel(private val getHistoryUseCase: GetHistoryUseCase) : ViewModel() {
    val historyLiveData: MutableLiveData<List<RequestDomainModel>> = MutableLiveData()

    fun getHistory() {
        historyLiveData.postValue(getHistoryUseCase.execute())
    }
}