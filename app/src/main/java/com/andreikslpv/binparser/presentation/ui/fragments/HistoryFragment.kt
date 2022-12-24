package com.andreikslpv.binparser.presentation.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreikslpv.binparser.databinding.FragmentHistoryBinding
import com.andreikslpv.binparser.domain.models.RequestDomainModel
import com.andreikslpv.binparser.presentation.ui.recyclers.HistoryRecyclerAdapter
import com.andreikslpv.binparser.presentation.vm.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var historyAdapter: HistoryRecyclerAdapter
    private val vm by viewModel<HistoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHistoryRecycler()
        vm.historyLiveData.observe(viewLifecycleOwner) {
            historyAdapter.changeItems(it)
            historyAdapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()

        vm.getHistory()
    }

    private fun initHistoryRecycler() {
        binding.historyRecycler.apply {
            //Инициализируем адаптер, в конструктор передаем анонимно инициализированный интерфейс
            historyAdapter =
                HistoryRecyclerAdapter(object : HistoryRecyclerAdapter.OnItemClickListener {
                    override fun click(request: RequestDomainModel) {
//                        (requireActivity() as MainActivity).launchDetailsFragment(
//                            App.instance.getFilmLocalStateUseCase.execute(film),
//                        )
                    }
                })
            //Присваиваем адаптер
            adapter = historyAdapter
            //Присвои layoutManager
            layoutManager = LinearLayoutManager(requireContext())
//            //Применяем декоратор для отступов
//            val decorator = TopSpacingItemDecoration(8)
//            addItemDecoration(decorator)
//            val callback = FilmTouchHelperCallback(adapter as FilmListRecyclerAdapter)
//            val touchHelper = ItemTouchHelper(callback)
//            touchHelper.attachToRecyclerView(this)
        }
    }
}