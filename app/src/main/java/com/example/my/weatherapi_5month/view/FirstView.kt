package com.example.my.weatherapi_5month.view

import com.example.my.weatherapi_5month.model.Model

interface FirstView {
    fun showWeather(weatherModel: Model)
    fun showError(message: String)
}