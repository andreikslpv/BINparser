package com.andreikslpv.binparser.domain

import com.andreikslpv.binparser.domain.models.BinDataDomainModel


interface ApiCallback {
    fun onSuccess(binData: BinDataDomainModel)
    fun onFailure(message: String)
}