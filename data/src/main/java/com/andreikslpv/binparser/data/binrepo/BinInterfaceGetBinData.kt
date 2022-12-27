package com.andreikslpv.binparser.data.binrepo

import com.andreikslpv.binparser.data.binrepo.dto.BinDataDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BinInterfaceGetBinData {
    @GET("{binNumber}")
    fun getBinData(
        @Path("binNumber") binNumber: String
    ): Call<BinDataDto>
}