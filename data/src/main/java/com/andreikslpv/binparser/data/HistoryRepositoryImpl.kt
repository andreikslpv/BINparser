package com.andreikslpv.binparser.data

import com.andreikslpv.binparser.domain.HistoryRepository
import com.andreikslpv.binparser.domain.models.RequestDomainModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class HistoryRepositoryImpl(private val file: File) : HistoryRepository {
    private val gson: Gson = Gson()

    override fun getHistory(): List<RequestDomainModel> {
        val json = if (file.isFile) file.readText(Charsets.UTF_8) else ""
        return gson.fromJson(json, object : TypeToken<ArrayList<RequestDomainModel?>?>() {}.type)
            ?: emptyList()
    }

    override fun addRequestToHistory(request: RequestDomainModel) {
        val listToSave = if (file.isFile) {
            // получаем в изменяемый список все сохраненные фильмы
            val mutableItems: MutableList<RequestDomainModel> = getHistory().toMutableList()
            mutableItems.add(request)
            mutableItems
        } else {
            val firstItemWhenFileIsEmpty = listOf(request)
            firstItemWhenFileIsEmpty
        }
        file.writeText(gson.toJson(listToSave))
    }

    override fun deleteRequestFromHistory(request: RequestDomainModel) {
        TODO("Not yet implemented")
    }
}