package com.egorshustov.weatherdemo.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.egorshustov.weatherdemo.data.CityAndCurrentWeather

@BindingAdapter("app:cities_and_current_weather")
fun RecyclerView.setCitiesAndCurrentWeather(citiesAndCurrentWeather: List<CityAndCurrentWeather>?) {
    (adapter as? CityAndCurrentWeatherAdapter)?.submitList(citiesAndCurrentWeather)
}

@BindingAdapter("app:imageFromUrl")
fun ImageView.bindImageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}