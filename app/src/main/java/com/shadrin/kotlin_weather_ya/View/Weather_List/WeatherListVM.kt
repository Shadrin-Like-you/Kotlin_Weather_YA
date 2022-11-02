package com.shadrin.kotlin_weather_ya.View.Weather_List

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.model.Repository
import com.shadrin.kotlin_weather_ya.model.RepositoryLocalImpl
import com.shadrin.kotlin_weather_ya.model.RepositoryRemoteImpl
import java.lang.Thread.sleep

class WeatherListVM(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) :
    ViewModel() {
    lateinit var repository: Repository

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repository = if (isConnection()) {
            RepositoryRemoteImpl()
        } else {
            RepositoryLocalImpl()
        }
    }

    fun sentRequest() {
        liveData.value = AppState.Loading
        if ((0..3).random() == 1) {
            liveData.postValue(AppState.Error(throw IllegalAccessException("Что-то сломалось :(")))
        } else {
            liveData.postValue(
                AppState.Success(
                    repository.getWeather(
                        55.755826,
                        37.617299900000035
                    )
                )
            )
        }
    }

    private fun isConnection(): Boolean {
        return false
    }

    override fun onCleared() { //Метод освобождает ресурсы
        super.onCleared()
    }
}