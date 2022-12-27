package com.andreikslpv.binparser.data.binrepo

import com.andreikslpv.binparser.data.binrepo.dto.BankDto
import com.andreikslpv.binparser.data.binrepo.dto.BinDataDto
import com.andreikslpv.binparser.data.binrepo.dto.CountryDto
import com.andreikslpv.binparser.data.binrepo.dto.NumberDto
import com.andreikslpv.binparser.domain.BaseMapper
import com.andreikslpv.binparser.domain.models.BankDomainModel
import com.andreikslpv.binparser.domain.models.BinDataDomainModel
import com.andreikslpv.binparser.domain.models.CountryDomainModel
import com.andreikslpv.binparser.domain.models.NumberDomainModel

object BinDataDtoToModel : BaseMapper<BinDataDto, BinDataDomainModel> {
    override fun map(type: BinDataDto?): BinDataDomainModel {
        return BinDataDomainModel(
            bank = BankDtoToModel.map(type?.bank),
            brand = type?.brand ?: "",
            country = CountryDtoToModel.map(type?.country),
            number = NumberDtoToModel.map(type?.number),
            prepaid = type?.prepaid ?: false,
            scheme = type?.scheme ?: "",
            type = type?.type ?: ""
        )
    }
}

object BankDtoToModel : BaseMapper<BankDto, BankDomainModel> {
    override fun map(type: BankDto?): BankDomainModel {
        return BankDomainModel(
            city = type?.city ?: "",
            name = type?.name ?: "",
            phone = type?.phone ?: "",
            url = type?.url ?: ""
        )
    }
}

object CountryDtoToModel : BaseMapper<CountryDto, CountryDomainModel> {
    override fun map(type: CountryDto?): CountryDomainModel {
        return CountryDomainModel(
            alpha2 = type?.alpha2 ?: "",
            currency = type?.currency ?: "",
            emoji = type?.emoji ?: "",
            latitude = type?.latitude ?: 200,
            longitude = type?.longitude ?: 200,
            name = type?.name ?: "",
            numeric = type?.numeric ?: ""
        )
    }
}

object NumberDtoToModel : BaseMapper<NumberDto, NumberDomainModel> {
    override fun map(type: NumberDto?): NumberDomainModel {
        return NumberDomainModel(
            length = type?.length ?: -1,
            luhn = type?.luhn ?: false
        )
    }
}