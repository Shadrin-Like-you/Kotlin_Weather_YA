package com.shadrin.kotlin_weather_ya.View.Weather_List.View_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.View.Weather_List.WeatherListFragment
import com.shadrin.kotlin_weather_ya.View.Weather_List.WeatherListVM
import com.shadrin.kotlin_weather_ya.databinding.FragmentDetailsBinding
import com.shadrin.kotlin_weather_ya.databinding.FragmentWeatherListBinding
import com.shadrin.kotlin_weather_ya.domain.Weather

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // прописываем 1 раз, чтобы потом не использовать в вызывах save call "?."
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
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

        val weather = (arguments?.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA))

        if (weather != null)
            renderData(weather)
    }

    private fun renderData(weather: Weather) {

        binding.cityName.text = weather.city.name
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"

    }

    companion object {
        const val BUNDLE_WEATHER_EXTRA = "BUNDLE_WEATHER EXTRA"
        fun newInstant(weather: Weather): DetailsFragment {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_WEATHER_EXTRA, weather)
            val fr = DetailsFragment()
            fr.arguments = bundle
            return fr
        }
    }

}