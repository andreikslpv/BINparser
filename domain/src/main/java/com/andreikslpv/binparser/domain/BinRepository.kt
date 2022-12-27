package com.andreikslpv.binparser.domain

interface BinRepository {
    fun getBinData(binNumber: String, callback: ApiCallback)
}