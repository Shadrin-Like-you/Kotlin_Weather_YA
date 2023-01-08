package com.shadrin.kotlin_weather_ya.View.Weather_List.View_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shadrin.kotlin_weather_ya.databinding.FragmentDetailsBinding
import com.shadrin.kotlin_weather_ya.domain.Weather
import com.shadrin.kotlin_weather_ya.utils.WeatherLoader

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // прописываем 1 раз, чтобы потом не использовать в вызывах save call "?."
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.let { arg ->
            arg.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA)

        }
        weather?.let { weatherLocal ->
            WeatherLoader.requestFirstVariant(weatherLocal.city.lat, weatherLocal.city.lon)
            { weatherDTO->
                requireActivity().runOnUiThread {
                    renderData(weatherLocal.apply {
                        weatherLocal.feelsLike = weatherDTO.fact.feelsLike
                        weatherLocal.temperature = weatherDTO.fact.temp
                    })
                }
            }
            WeatherLoader.requestSecondVariant(weatherLocal.city.lat,weatherLocal.city.lon)
            {weatherDTO->
                requireActivity().runOnUiThread {
                    renderData(weatherLocal.apply {
                        weatherLocal.feelsLike = weatherDTO.fact.feelsLike
                        weatherLocal.temperature = weatherDTO.fact.temp
                    })
                }
            }
        }

    }

    private fun renderData(weather: Weather) {
        with(binding) {
            cityName.text = weather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }


    }

    companion object {
        const val BUNDLE_WEATHER_EXTRA = "BUNDLE_WEATHER EXTRA"
        fun newInstant(weather: Weather) = DetailsFragment().also { fr ->
            fr.arguments = Bundle().apply {
                putParcelable(BUNDLE_WEATHER_EXTRA, weather)
                putParcelable(BUNDLE_WEATHER_EXTRA, weather)
            }
            fr.arguments = Bundle().also {
                it.putParcelable(BUNDLE_WEATHER_EXTRA, weather)
                it.putParcelable(BUNDLE_WEATHER_EXTRA, weather)
            }
        }
    }

}