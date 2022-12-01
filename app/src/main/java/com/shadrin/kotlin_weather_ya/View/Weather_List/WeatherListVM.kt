package com.shadrin.kotlin_weather_ya.View.Weather_List

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.model.*
import java.lang.Thread.sleep

class WeatherListVM(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) :
    ViewModel() {
    lateinit var repositoryAll: RepositoryAll
    lateinit var repositorySingle: RepositorySingle

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repositorySingle = if (isConnection()) {
            RepositoryRemoteImpl()
        } else {
            RepositoryLocalImpl()
        }
        repositoryAll = RepositoryLocalImpl()
    }

  fun getWeatherListForRussia(){
      sentRequest(Location.Russian)
  }
    fun getWeatherListForWorld(){
        sentRequest(Location.World)
    }
   private fun sentRequest(location: Location) {
        liveData.value = AppState.Loading
        if ((0..3).random() == 1) {
            liveData.postValue(AppState.Error(throw IllegalAccessException("Что-то сломалось :(")))
        } else {
            liveData.postValue(
                AppState.SuccessAll(repositoryAll.getListWeather(location))
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