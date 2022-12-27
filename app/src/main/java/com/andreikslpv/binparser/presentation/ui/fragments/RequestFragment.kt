package com.andreikslpv.binparser.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.andreikslpv.binparser.R
import com.andreikslpv.binparser.databinding.FragmentRequestBinding
import com.andreikslpv.binparser.domain.models.RequestDomainModel
import com.andreikslpv.binparser.domain.usecase.AddRequestToHistoryUseCase
import com.andreikslpv.binparser.presentation.vm.RequestViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


const val RESPONSE_NOT_FOUND = "404"
const val RESPONSE_HIT_LIMIT = "429"

class RequestFragment : Fragment() {
    private var _binding: FragmentRequestBinding? = null
    private val binding
        get() = _binding!!
    private val addRequestToHistoryUseCase: AddRequestToHistoryUseCase by inject()
    private val vm by viewModel<RequestViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.binDataLiveData.observe(viewLifecycleOwner) {
            val number = if (it.number.length == -1) {
                ""
            } else {
                it.number.length.toString()
            }
            if (it.number.luhn)
                binding.cardNumberDetails.text = getString(R.string.card_number_details, number, getString(R.string.yes))
            else
                binding.cardNumberDetails.text = getString(R.string.card_number_details, number, getString(R.string.no))
            binding.cardScheme.text = getString(R.string.card_scheme, it.scheme)
            binding.cardBrand.text = getString(R.string.card_brand, it.brand)
            binding.cardType.text = getString(R.string.card_type, it.type)
            if (it.prepaid)
                binding.cardPrepaid.text = getString(R.string.card_prepaid, getString(R.string.yes))
            else
                binding.cardPrepaid.text = getString(R.string.card_prepaid, getString(R.string.no))
            binding.bankName.text = getString(R.string.bank_name, it.bank.name)
            binding.bankCity.text = getString(R.string.bank_city, it.bank.city)
            binding.bankUrl.text = getString(R.string.bank_url, it.bank.url)
            binding.bankPhone.text = getString(R.string.bank_phone, it.bank.phone)
            if (it.country.name.isNotBlank()) {
                binding.countryName.text = getString(
                    R.string.country_name,
                    it.country.name,
                    it.country.alpha2,
                    it.country.emoji,
                    it.country.numeric,
                    it.country.currency
                )
            } else {
                binding.countryName.text = ""
            }
            if (it.country.latitude == 200 || it.country.longitude == 200) {
                binding.countryCoordinate.text = ""
            } else {
                binding.countryCoordinate.text =
                    getString(
                        R.string.country_coordinate,
                        it.country.latitude,
                        it.country.longitude
                    )
            }
        }

        vm.apiResponseMessage.observe(viewLifecycleOwner) {
            val message = when (it) {
                RESPONSE_NOT_FOUND -> getString(R.string.response_404)
                RESPONSE_HIT_LIMIT -> getString(R.string.response_429)
                else -> it
            }
            Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
        }

        binding.requestButton.setOnClickListener {
            requestBin()
        }

        binding.requestEditText.setOnEditorActionListener { _, _, event ->
            // Если нажата кнопка Enter на soft клавиатуре, то запускаем проверку BIN
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                requestBin()
            }
            false
        }

        binding.countryCoordinate.setOnClickListener {
            val latitude = vm.binDataLiveData.value?.country?.latitude ?: 200
            val longitude = vm.binDataLiveData.value?.country?.longitude ?: 200
            if (binding.countryCoordinate.text.isNotBlank() && latitude != 200 && longitude != 200) {
                val geoUrl = "geo:$latitude,$longitude?z=22&q=$latitude,$longitude"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUrl))
                startActivity(intent)
            }
        }
    }

    private fun requestBin() {
        val request = binding.requestEditText.text.toString()
        if (request.isBlank()) {
            Toast.makeText(view?.context, getString(R.string.empty_request), Toast.LENGTH_SHORT)
                .show()
        } else {
            // отпраявляем запрос на получение данных о BIN
            vm.getBinData(request)
            // добавляем сведения о произведенном запросе в историю
            addRequestToHistoryUseCase.execute(RequestDomainModel(request, Date()))
            // скрываем soft клавиатуру
            val inputMethodManager: InputMethodManager? =
                view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            inputMethodManager?.hideSoftInputFromWindow(
                binding.requestEditText.applicationWindowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}