package com.andreikslpv.binparser.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.andreikslpv.binparser.R
import com.andreikslpv.binparser.databinding.ActivityMainBinding
import com.andreikslpv.binparser.presentation.ui.fragments.HistoryFragment
import com.andreikslpv.binparser.presentation.ui.fragments.RequestFragment
import com.andreikslpv.binparser.presentation.ui.utils.FragmentsType

const val BUNDLE_KEY_REQUEST = "request"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigationMenu()
        // если первый, то запускаем фрагмент Request
        if (savedInstanceState == null)
            changeFragment(RequestFragment(), FragmentsType.REQUEST)
    }

    private fun initBottomNavigationMenu() {
        binding.bottomNavigation.setOnItemSelectedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentPlaceholder)
            when (it.itemId) {
                R.id.request -> {
                    // если активен не RequestFragment, то запускаем RequestFragment
                    if (currentFragment !is RequestFragment) {
                        val fragment = checkFragmentExistence(FragmentsType.REQUEST)
                        //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                        //элвиса вызываем создание нового фрагмента
                        changeFragment(fragment ?: RequestFragment(), FragmentsType.REQUEST)
                    }
                    true
                }
                R.id.history -> {
                    if (currentFragment !is HistoryFragment) {
                        val fragment = checkFragmentExistence(FragmentsType.HISTORY)
                        changeFragment(fragment ?: HistoryFragment(), FragmentsType.HISTORY)
                    }
                    true
                }
                else -> false
            }
        }
    }

    //Ищем фрагмент по тегу, если он есть то возвращаем его, если нет, то null
    private fun checkFragmentExistence(type: FragmentsType): Fragment? =
        supportFragmentManager.findFragmentByTag(type.tag)

    private fun changeFragment(fragment: Fragment, type: FragmentsType) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentPlaceholder, fragment, type.tag)
            .addToBackStack(null)
            .commit()
    }

    fun launchRequestFragment(request: String) {
        val bundle = Bundle()
        bundle.putString(BUNDLE_KEY_REQUEST, request)
        var fragment = checkFragmentExistence(FragmentsType.REQUEST)
        if (fragment == null)
            fragment = RequestFragment()
        fragment.arguments = bundle
        changeFragment(fragment, FragmentsType.REQUEST)
    }

}