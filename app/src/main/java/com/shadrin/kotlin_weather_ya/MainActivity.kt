package com.shadrin.kotlin_weather_ya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.shadrin.kotlin_weather_ya.View.Weather_List.WeatherListFragment
import com.shadrin.kotlin_weather_ya.databinding.ActivityMainBinding
import android.view.View.OnClickListener as OnClickListener

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
        if (savedInstanceState==null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, WeatherListFragment.newInstant()).commit()      }

       /* findViewById<AppCompatButton>(R.id.btn1).setOnClickListener(object : OnClickListener {


           override fun onClick(v: View?) {

            }

        })

        */
        val dataClass1 = City(name = "Barselona", weather = R.color.purple_200)
        val dataClass2 = dataClass1.copy(name = "Madrid")

        fun city() {
            val cites = ArrayList<City>()
            cites.add(City("New York", R.color.teal_200))
            cites.add(City("Valencia", R.color.purple_200))
            cites.add(City("London", R.color.white))

            for ((d, r) in cites) {
                println("$d, $r")
            }
        }

        Log.d("City", dataClass1.toString())
        Log.d("City", dataClass2.toString())
        println(dataClass2)


    }

}

/* GET https://api.weather.yandex.ru/v2/informers?
   lat=<широта>
   & lon=<долгота>
   & [lang=<язык ответа>]

   X-Yandex-API-Key: <значение ключа>
*/

