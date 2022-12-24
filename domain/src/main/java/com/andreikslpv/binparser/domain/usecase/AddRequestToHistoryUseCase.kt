package com.andreikslpv.binparser.domain.usecase

import com.andreikslpv.binparser.domain.HistoryRepository
import com.andreikslpv.binparser.domain.models.RequestDomainModel

class AddRequestToHistoryUseCase(private val historyRepository: HistoryRepository) {
    fun execute(request: RequestDomainModel) {
        historyRepository.addRequestToHistory(request)
    }
}