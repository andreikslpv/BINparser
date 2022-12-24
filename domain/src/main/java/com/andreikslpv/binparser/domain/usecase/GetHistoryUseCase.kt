package com.andreikslpv.binparser.domain.usecase

import com.andreikslpv.binparser.domain.HistoryRepository
import com.andreikslpv.binparser.domain.models.RequestDomainModel

class GetHistoryUseCase(private val historyRepository: HistoryRepository) {
    fun execute() : List<RequestDomainModel>{
        return historyRepository.getHistory()
    }
}