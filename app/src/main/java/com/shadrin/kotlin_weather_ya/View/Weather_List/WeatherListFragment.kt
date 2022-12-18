package com.shadrin.kotlin_weather_ya.View.Weather_List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.shadrin.kotlin_weather_ya.R
import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.VM.WeatherListVM
import com.shadrin.kotlin_weather_ya.View.Weather_List.View_details.DetailsFragment
import com.shadrin.kotlin_weather_ya.View.Weather_List.View_details.OnItemClick
import com.shadrin.kotlin_weather_ya.databinding.FragmentWeatherListBinding
import com.shadrin.kotlin_weather_ya.domain.Weather
import java.time.Duration

class WeatherListFragment : Fragment(), OnItemClick {
    companion object {
        fun newInstant() = WeatherListFragment()
    }

    var isRussian = true
    private var _binding: FragmentWeatherListBinding? = null

    // прописываем 1 раз, чтобы потом не использовать в вызывах save call "?."
    private val binding: FragmentWeatherListBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    lateinit var ViewModel: WeatherListVM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ViewModelProvider класс, который возвращает наш инстанс VM
        ViewModel = ViewModelProvider(this).get(WeatherListVM::class.java)
        /*
        Используя ::class, вы получаете экземпляр KClass. Это API-интерфейс Kotlin Reflection,
        который может обрабатывать такие функции Kotlin, как свойства, классы данных и т.д.

        Используя ::class.java, вы получаете экземпляр класса. Это Java Reflection API,
        который взаимодействует с любым кодом отражения Java, но не может работать с некоторыми
        функциями Kotlin.
         */
        ViewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                //    Toast.makeText(requireContext(), "It Work!$t", Toast.LENGTH_LONG).show()
                renderData(t)
            }
            /*
            liveData - предназначен для хранения объекта и разрешает подписаться на его изменения.
            Знает Жизненый Цикл "подписчика".
             */

        })

        binding.weatherListFragmentFAB.setOnClickListener {
            isRussian = !isRussian
            if (isRussian) {
                ViewModel.getWeatherListForRussia()
                binding.weatherListFragmentFAB.apply {
                    setImageResource(R.drawable.ic_russia)
                }
            } else {
                ViewModel.getWeatherListForWorld()
                binding.weatherListFragmentFAB.setImageResource(R.drawable.ic_world)
            }
        }
        ViewModel.getWeatherListForRussia()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.showResult()
                binding.root.HomeWork_4_2("Ошибка!", Snackbar.LENGTH_SHORT, "Повторить?") { _ -> }
                if (isRussian) {
                    ViewModel.getWeatherListForRussia()
                } else {
                    ViewModel.getWeatherListForWorld()
                }
            }
            AppState.Loading -> {
                binding.loading()
            }
            is AppState.SuccessSingle -> {
                binding.showResult()
                val result = appState.weatherData
            }
            is AppState.SuccessAll -> {
                binding.showResult()
                binding.mainFragmentRecyclerView.adapter =
                    WeatherListAdapter(appState.weatherList, this)
            }
        }
    }

    fun View.HomeWork_4_1(string: String, duration: Int) {
        Snackbar.make(this, string, duration).show()
    }

    fun View.HomeWork_4_2(
        string: String,
        duration: Int,
        actionString: String,
        block: (v: View) -> Unit
    ) {
        Snackbar.make(this, string, duration).setAction("Повторить?", block).show()
    }

    fun FragmentWeatherListBinding.loading() {
        this.mainFragmentLoadingLayout.visibility = View.VISIBLE
        this.weatherListFragmentFAB.visibility = View.GONE
    }

    fun FragmentWeatherListBinding.showResult() {
        this.mainFragmentLoadingLayout.visibility = View.VISIBLE
        this.weatherListFragmentFAB.visibility = View.GONE
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().hide(this).add(
            //hide(this).add - скрывает рание клики, возвращая к последнему экрану
            R.id.container, DetailsFragment.newInstant(weather)
        ).addToBackStack("").commit()
    }
}