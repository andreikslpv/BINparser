package com.andreikslpv.binparser.domain.usecase

import com.andreikslpv.binparser.domain.ApiCallback
import com.andreikslpv.binparser.domain.BinRepository

class GetBinDataUseCase(private val binRepository: BinRepository) {

    fun execute(binNumber: String, callback: ApiCallback) {
        binRepository.getBinData(binNumber, callback)
    }
}