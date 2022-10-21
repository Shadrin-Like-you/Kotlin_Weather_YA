package com.shadrin.kotlin_weather_ya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import android.view.View.OnClickListener as OnClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<AppCompatButton>(R.id.btn1).setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

            }

        })
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

