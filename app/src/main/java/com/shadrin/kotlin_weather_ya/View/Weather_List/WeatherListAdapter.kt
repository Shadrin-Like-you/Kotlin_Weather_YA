package com.shadrin.kotlin_weather_ya.View.Weather_List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shadrin.kotlin_weather_ya.MainActivity
import com.shadrin.kotlin_weather_ya.R
import com.shadrin.kotlin_weather_ya.View.Weather_List.View_details.DetailsFragment
import com.shadrin.kotlin_weather_ya.View.Weather_List.View_details.OnItemClick
import com.shadrin.kotlin_weather_ya.databinding.FragmentWeatherListBinding
import com.shadrin.kotlin_weather_ya.databinding.FragmentWeatherListRecyclerItemBinding
import com.shadrin.kotlin_weather_ya.domain.Weather

class WeatherListAdapter(private val dataList:List<Weather>, private val callback: OnItemClick):RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> (){

    //Создаем контейнер
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = FragmentWeatherListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding.root)
    }
    //Связываем контейнер со значением
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
       holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    // inner - для доступа к внешним полям
   inner class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(weather: Weather){
    val binding = FragmentWeatherListRecyclerItemBinding.bind(itemView)
            binding.cityName.text = weather.city.name
            binding.root.setOnClickListener {
                callback.onItemClick(weather)
            }
        }
    }
}