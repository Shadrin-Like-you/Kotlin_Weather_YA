package com.shadrin.kotlin_weather_ya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
*/

