package com.andreikslpv.binparser.data.binrepo.dto


import com.google.gson.annotations.SerializedName

data class NumberDto(
    @SerializedName("length")
    val length: Int?,
    @SerializedName("luhn")
    val luhn: Boolean?
)