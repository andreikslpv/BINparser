package com.andreikslpv.binparser.domain.models

data class CountryDomainModel(
    val alpha2: String = "",
    val currency: String = "",
    val emoji: String = "",
    val latitude: Int = 200,
    val longitude: Int = 200,
    val name: String = "",
    val numeric: String = ""
)
