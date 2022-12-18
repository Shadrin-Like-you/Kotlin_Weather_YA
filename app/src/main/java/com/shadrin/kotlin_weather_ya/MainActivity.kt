package com.shadrin.kotlin_weather_ya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shadrin.kotlin_weather_ya.View.Weather_List.WeatherListFragment
import com.shadrin.kotlin_weather_ya.databinding.ActivityMainBinding

internal class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    /*
     lateinit - отложенная инициализация, позволяет избежать NullPointerException (null) пока нет
       инфы по переменной.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
        //binding.btn1.text = "WTF!"
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstant()).commit()
        }

    }

}

/* GET https://api.weather.yandex.ru/v2/informers?
   lat=<широта>
   & lon=<долгота>
   & [lang=<язык ответа>]

   X-Yandex-API-Key: <значение ключа>

   Сортировка с помощью лямбд:
    var numList = listOf(3,5,2,9,45,23,1).also {
        it.sorted() //сортируем
    }.also {
        it.map { "$it" } // записываем новый список (необязательно)
    }.let { it.first() } // возвращаем первый (он же минимум) элемент

   Пример по замене значений с помощью лямбды
    var lat: Double = 1.0
    var lon: Double = 2.0
    Log.d("@@@","lat: ${lat} lon: ${lat}")
    lat = lon.apply{
        lon = lat
    }
    Log.d("@@@","lat: ${lat} lon: ${lat} ")

    результат:
    было @@@ lat: 1.0 lon: 2.0
    стало @@@ lat: 2.0 lon: 1.0
*/

