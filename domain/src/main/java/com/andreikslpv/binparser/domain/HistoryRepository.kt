package com.andreikslpv.binparser.domain

import com.andreikslpv.binparser.domain.models.RequestDomainModel

interface HistoryRepository {
    fun getHistory(): List<RequestDomainModel>

    fun addRequestToHistory(request: RequestDomainModel)

    fun deleteRequestFromHistory(request: RequestDomainModel)
}