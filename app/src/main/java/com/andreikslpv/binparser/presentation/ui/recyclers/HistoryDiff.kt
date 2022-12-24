package com.andreikslpv.binparser.presentation.ui.recyclers

import androidx.recyclerview.widget.DiffUtil
import com.andreikslpv.binparser.domain.models.RequestDomainModel

class HistoryDiff(private val oldList: List<RequestDomainModel>, private val newList: List<RequestDomainModel>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}