package com.shadrin.kotlin_weather_ya.VM

import com.shadrin.kotlin_weather_ya.domain.Weather

sealed class AppState {
    /*
    Запечатанные классы и интерфейсы представляют собой ограниченные иерархии классов,
     обеспечивающие больший контроль над наследованием. Все прямые подклассы запечатанного класса
     известны во время компиляции. Никакие другие подклассы не могут появляться вне модуля, внутри
     которого определен запечатанный класс.
     */
    data class SuccessSingle(val weatherData: Weather) : AppState()
    data class SuccessAll(val weatherList: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState() // Добавить отображение загрузки ч/з ProgressBar
    /*
    Методы для работы с ProgressBar:

setProgress() — устанавливает заданное значение индикатора прогресса;
getProgress() — возвращает текущее значение индикатора прогресса;
incrementProgressBy() — устанавливает величину дискретизации приращения значения индикатора;
setMax() — устанавливает максимальное значение величины прогресса.
     */

}