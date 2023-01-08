package com.shadrin.kotlin_weather_ya.VM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shadrin.kotlin_weather_ya.model.*
import kotlin.random.Random

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
            Thread.sleep(3000L)
            if ((0..3).random(Random(System.currentTimeMillis())) ==1) {
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