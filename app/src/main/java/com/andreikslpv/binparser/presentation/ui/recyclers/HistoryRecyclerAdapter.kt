package com.andreikslpv.binparser.presentation.ui.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andreikslpv.binparser.databinding.ItemHistoryBinding
import com.andreikslpv.binparser.domain.models.RequestDomainModel


//в параметр передаем слушатель, чтобы потом могли обрабатывать нажатия из класса Activity
class HistoryRecyclerAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<RequestDomainModel>()

    override fun getItemCount() = items.size

    //В этом методе привязываем ViewHolder и передаем туда "надутую" верстку
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryViewHolder(
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    //В этом методе будет привязка полей из объекта RequestDomainModel к View из history_item.xml
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Проверяем какой у нас ViewHolder
        when (holder) {
            is HistoryViewHolder -> {
                //Вызываем метод bind(), который мы создали, и передаем туда объект
                //из нашей базы данных с указанием позиции
                holder.bind(items[position])
                //Обрабатываем нажатие на весь элемент целиком и вызываем метод листенера, который получаем из
                //конструктора адаптера
                holder.binding.itemContainer.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }

    //Метод для добавления объектов в список
    fun changeItems(list: List<RequestDomainModel>) {
        val diff = HistoryDiff(items, list)
        val diffResult = DiffUtil.calculateDiff(diff)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }


    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(request: RequestDomainModel)
    }
}