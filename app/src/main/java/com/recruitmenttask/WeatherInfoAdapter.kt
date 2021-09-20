package com.recruitmenttask

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recruitmenttask.databinding.ItemWeatherInfoBinding


class WeatherInfoAdapter(private val weatherInfo: List<WeatherInfo>): RecyclerView.Adapter<WeatherInfoAdapter.WeatherInfoViewHolder>() {

    inner class WeatherInfoViewHolder(binding: ItemWeatherInfoBinding): RecyclerView.ViewHolder(binding.root){
        val city: TextView = binding.city
        val weather: TextView = binding.weather
        val hourlyTemp: RecyclerView = binding.hourlyTempRecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherInfoViewHolder {
        return WeatherInfoViewHolder(ItemWeatherInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: WeatherInfoViewHolder, position: Int) {
        val cityInfo = weatherInfo[position]
        holder.city.text = cityInfo.city
        holder.weather.text = cityInfo.weather
        holder.hourlyTemp.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = HourlyTempAdapter(cityInfo.hourly_temp)
        }
    }

    override fun getItemCount(): Int {
        return weatherInfo.size
    }
}