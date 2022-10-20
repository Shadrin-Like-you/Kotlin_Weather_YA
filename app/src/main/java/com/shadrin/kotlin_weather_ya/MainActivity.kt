package com.shadrin.kotlin_weather_ya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import android.view.View.OnClickListener as OnClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    findViewById<AppCompatButton>(R.id.btn1).setOnClickListener(object : OnClickListener{
        override fun onClick(v: View?) {

        }

    })
    }
}