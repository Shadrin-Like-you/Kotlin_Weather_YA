package com.shadrin.kotlin_weather_ya.View.Weather_List

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.VM.AppState.Success
import java.lang.Thread.sleep

class WeatherListVM(val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel() {

    fun sentRequest() {
        liveData.value = AppState.Loading// Загрузка
        Thread {
            sleep(2000L) // Запрос в репозиторий
            liveData.postValue (AppState.Success(Any())) // Ответ
        }.start()

    }
}