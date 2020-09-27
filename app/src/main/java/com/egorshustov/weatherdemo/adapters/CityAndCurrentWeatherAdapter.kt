package com.egorshustov.weatherdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egorshustov.weatherdemo.citylist.CityListViewModel
import com.egorshustov.weatherdemo.data.CityAndCurrentWeather
import com.egorshustov.weatherdemo.databinding.ItemCityAndCurrentWeatherBinding

class CityAndCurrentWeatherAdapter(private val viewModel: CityListViewModel) :
    ListAdapter<CityAndCurrentWeather, CityAndCurrentWeatherAdapter.ViewHolder>(
        CityAndCurrentWeatherDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(viewModel, it) }
    }

    class ViewHolder private constructor(val binding: ItemCityAndCurrentWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: CityListViewModel, item: CityAndCurrentWeather) = with(binding) {
            viewmodel = viewModel
            cityandcurrentweather = item
            binding.textTemperature.text = if (item.currentWeather?.temperature == null) {
                ""
            } else {
                String.format("%.1f", item.currentWeather.temperature) + "°"
            }
            executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemCityAndCurrentWeatherBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CityAndCurrentWeatherDiffCallback : DiffUtil.ItemCallback<CityAndCurrentWeather>() {

    override fun areItemsTheSame(oldItem: CityAndCurrentWeather, newItem: CityAndCurrentWeather) =
        oldItem.city.id == newItem.city.id

    override fun areContentsTheSame(
        oldItem: CityAndCurrentWeather,
        newItem: CityAndCurrentWeather
    ) = oldItem.city.name == newItem.city.name
            && oldItem.currentWeather?.description == newItem.currentWeather?.description
            && oldItem.currentWeather?.temperature == newItem.currentWeather?.temperature
            && oldItem.currentWeather?.iconUrl == newItem.currentWeather?.iconUrl
}
