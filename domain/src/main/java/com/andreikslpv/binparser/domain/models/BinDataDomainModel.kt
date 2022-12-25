package com.andreikslpv.binparser.domain.models

data class BinDataDomainModel(
    val bank: Any,
    val brand: String,
    val country: Any,
    val number: Any,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)
