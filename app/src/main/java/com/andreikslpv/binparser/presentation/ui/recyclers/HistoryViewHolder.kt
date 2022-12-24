package com.andreikslpv.binparser.presentation.ui.recyclers

import androidx.recyclerview.widget.RecyclerView
import com.andreikslpv.binparser.databinding.ItemHistoryBinding
import com.andreikslpv.binparser.domain.models.RequestDomainModel
import java.text.SimpleDateFormat

class HistoryViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    // кладем данные из RequestDomainModel в View
    fun bind(request: RequestDomainModel) {
        binding.historyRequest.text = request.bin
        val formatter = SimpleDateFormat.getDateTimeInstance()
        binding.historyDate.text = formatter.format(request.date)
    }
}