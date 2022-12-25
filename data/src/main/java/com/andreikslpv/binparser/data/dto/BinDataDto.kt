package com.andreikslpv.binparser.data.dto


import com.google.gson.annotations.SerializedName

data class BinDataDto(
    @SerializedName("bank")
    val bank: BankDto?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("country")
    val country: CountryDto?,
    @SerializedName("number")
    val number: NumberDto?,
    @SerializedName("prepaid")
    val prepaid: Boolean?,
    @SerializedName("scheme")
    val scheme: String?,
    @SerializedName("type")
    val type: String?
)