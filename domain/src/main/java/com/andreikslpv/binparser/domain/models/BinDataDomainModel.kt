package com.andreikslpv.binparser.domain.models

data class BinDataDomainModel(
    val bank: BankDomainModel = BankDomainModel(),
    val brand: String = "",
    val country: CountryDomainModel = CountryDomainModel(),
    val number: NumberDomainModel = NumberDomainModel(),
    val prepaid: Boolean = false,
    val scheme: String = "",
    val type: String = ""
)
