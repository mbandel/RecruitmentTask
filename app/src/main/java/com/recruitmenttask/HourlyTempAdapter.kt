package com.recruitmenttask

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.recruitmenttask.databinding.ItemHourlyTempBinding

class HourlyTempAdapter(private val hourlyTempList:List<HourlyTemp>): RecyclerView.Adapter<HourlyTempAdapter.HourlyTempViewHolder>() {

    inner class HourlyTempViewHolder(binding: ItemHourlyTempBinding): RecyclerView.ViewHolder(binding.root) {
        val hour: TextView = binding.hour
        val temp: TextView = binding.temp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyTempViewHolder {
        return HourlyTempViewHolder(ItemHourlyTempBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: HourlyTempViewHolder, position: Int) {
        val hourlyTemp = hourlyTempList[position]
        holder.hour.text = hourlyTemp.hour.toString()
        holder.temp.text = hourlyTemp.temp.toString()
    }

    override fun getItemCount(): Int {
        return hourlyTempList.size
    }
}
