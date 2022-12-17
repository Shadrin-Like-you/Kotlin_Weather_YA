package com.shadrin.kotlin_weather_ya.VM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.model.*

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

    fun getWeatherListForRussia() {
        sentRequest(Location.Russian)
    }

    fun getWeatherListForWorld() {
        sentRequest(Location.World)
    }

    private fun sentRequest(location: Location) {
        liveData.value = AppState.Loading
        Thread {
            Thread.sleep(200L)
            if (false) {
                liveData.postValue(AppState.Error(IllegalAccessException("Что-то сломалось :(")))
            } else {
                liveData.postValue(
                    AppState.SuccessAll(repositoryAll.getListWeather(location))
                )
            }
        }.start()

    }

    private fun isConnection(): Boolean {
        return false
    }

    override fun onCleared() { //Метод освобождает ресурсы
        super.onCleared()
    }
}