package com.andreikslpv.binparser.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreikslpv.binparser.databinding.FragmentRequestBinding
import com.andreikslpv.binparser.domain.models.RequestDomainModel
import com.andreikslpv.binparser.domain.usecase.AddRequestToHistoryUseCase
import org.koin.android.ext.android.inject
import java.util.*

class RequestFragment : Fragment() {
    private var _binding: FragmentRequestBinding? = null
    private val binding
        get() = _binding!!
    private val addRequestToHistoryUseCase: AddRequestToHistoryUseCase by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.requestButton.setOnClickListener {
            addRequestToHistoryUseCase.execute(
                RequestDomainModel(
                    binding.requestEditText.text.toString(),
                    Date()
                )
            )
        }
    }
}