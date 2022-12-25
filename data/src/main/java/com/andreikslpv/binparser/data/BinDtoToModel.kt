package com.andreikslpv.binparser.data

import com.andreikslpv.binparser.data.dto.BinDataDto
import com.andreikslpv.binparser.domain.BaseMapper
import com.andreikslpv.binparser.domain.models.BankDomainModel
import com.andreikslpv.binparser.domain.models.BinDataDomainModel
import com.andreikslpv.binparser.domain.models.CountryDomainModel
import com.andreikslpv.binparser.domain.models.NumberDomainModel

object BinDtoToModel : BaseMapper<BinDataDto, BinDataDomainModel> {
    override fun map(type: BinDataDto?): BinDataDomainModel {
        return BinDataDomainModel(
            bank = type?.bank ?: BankDomainModel(),
            brand = type?.brand ?: "",
            country = type?.country ?: CountryDomainModel(),
            number = type?.number ?: NumberDomainModel(),
            prepaid = type?.prepaid ?: false,
            scheme = type?.scheme ?: "",
            type = type?.type ?: ""
            )
    }
}