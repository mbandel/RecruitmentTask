package com.recruitmenttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.recruitmenttask.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherInfoList = initData()
        printOutput(weatherInfoList)
        loadWeatherRecyclerView(weatherInfoList)
    }

    private fun printOutput(weatherInfoList: List<WeatherInfo>) {
        try {
            println("Smallest temperature across all cities: ${findMinValue(weatherInfoList)}")
            val cityTemp: MutableMap<String, Double> = findMaxValueForEachCity(weatherInfoList)
            for ((city, temp) in cityTemp) {
                println("$city: $temp")
            }
            println("Smallest average daily temperature: ${findLowestAverageTempCity(weatherInfoList)}")
        }catch (exception: EmptyListException){
            println(exception.message)
        }
    }

    //function filling json data into list
    private fun initData(): List<WeatherInfo> {
        val gson = Gson()
        val weatherInfoType = object: TypeToken<List<WeatherInfo>>(){}.type
        return gson.fromJson(JsonInfo.data, weatherInfoType)
    }

    //function returning minimum temperature value for all cities
    private fun findMinValue(weatherInfoList: List<WeatherInfo>): Double {
        if (weatherInfoList.isEmpty()) {
            throw EmptyListException(getString(R.string.empty_list))
        }
        val tempEachCity: MutableList<Double> = mutableListOf()
        for (cityWeather: WeatherInfo in weatherInfoList){
           tempEachCity.add(cityWeather.hourly_temp.minOf { it.temp })
        }
       return tempEachCity.minOf { it }
    }

    //function returning maximum temperature value for each city
    private fun findMaxValueForEachCity(weatherInfoList: List<WeatherInfo>): MutableMap<String, Double> {
        if (weatherInfoList.isEmpty()) {
            throw EmptyListException(getString(R.string.empty_list))
        }
        val highestTempEachCity: MutableMap<String, Double> = mutableMapOf()
        for (cityWeather: WeatherInfo in weatherInfoList) {
            highestTempEachCity[cityWeather.city] = cityWeather.hourly_temp.maxOf { it.temp }
            }
        return highestTempEachCity
    }

    //function returning city with lowest average temperature
    private fun findLowestAverageTempCity(weatherInfoList: List<WeatherInfo>): String? {
        if (weatherInfoList.isEmpty()) {
            throw EmptyListException(getString(R.string.empty_list))
        }
        val avgTempMap: MutableMap<String, Double> = mutableMapOf()
        for (cityWeather: WeatherInfo in weatherInfoList){
            avgTempMap[cityWeather.city] = cityWeather.hourly_temp.map { it.temp }.average()
        }
        val lowestAvgTemp = avgTempMap.minByOrNull { it.value }
        return lowestAvgTemp?.key

    }

    //function setting recycler view
    private fun loadWeatherRecyclerView(weatherInfo: List<WeatherInfo>) {
        binding.weatherRecyclerView.apply{
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = WeatherInfoAdapter(weatherInfo)
        }
    }

}