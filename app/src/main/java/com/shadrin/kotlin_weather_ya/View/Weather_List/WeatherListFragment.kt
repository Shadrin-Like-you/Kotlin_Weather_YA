package com.shadrin.kotlin_weather_ya.View.Weather_List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.databinding.FragmentWeatherListBinding

class WeatherListFragment : Fragment() {
    companion object {
        fun newInstant() = WeatherListFragment()
    }

    private var _bilding: FragmentWeatherListBinding? = null // прописываем 1 раз, чтобы потом
    // не использовать в вызывах save call "?."
    private val bilding: FragmentWeatherListBinding
        get() {
            return _bilding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _bilding = null
    }

    lateinit var ViewModel: WeatherListVM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bilding = FragmentWeatherListBinding.inflate(inflater)
        return bilding.root
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
        ViewModel.sentRequest()

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {/*TODO HW*/
            }
            AppState.Loading -> {/*TODO HW*/
            }
            is AppState.Success -> {

                val result = appState.weatherData
                bilding.cityName.text = result.city.name
                bilding.temperatureValue.text = result.temperature.toString()
                bilding.feelsLikeValue.text = result.feelsLike.toString()
                bilding.cityCoordinates.text = "${result.city.lat}/${result.city.lon}"

            }
        }
    }
}