package com.andreikslpv.binparser.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreikslpv.binparser.domain.ApiCallback
import com.andreikslpv.binparser.domain.models.BinDataDomainModel
import com.andreikslpv.binparser.domain.usecase.GetBinDataUseCase


class RequestViewModel(private val getBinDataUseCase: GetBinDataUseCase) : ViewModel() {
    val binDataLiveData: MutableLiveData<BinDataDomainModel> = MutableLiveData()
    val apiResponseMessage: MutableLiveData<String> = MutableLiveData()
    val editTextLiveData: MutableLiveData<String> = MutableLiveData()


    init {
        binDataLiveData.postValue(BinDataDomainModel())
    }

    fun getBinData(binNumber: String) {
        getBinDataUseCase.execute(
            binNumber,
            object : ApiCallback {
                override fun onSuccess(binData: BinDataDomainModel) {
                    binDataLiveData.postValue(binData)
                }

                override fun onFailure(message: String) {
                    // В случае неудачи ...
                    // ... расшифровываем и показываем сообщение об ошибке
                    apiResponseMessage.postValue(message)
                    // ... очищаем поля экрана
                    binDataLiveData.postValue(BinDataDomainModel())
                }
            })
    }

    fun setEditText(text: String?) {
        if (text != null)
            editTextLiveData.postValue(text!!)
    }
}