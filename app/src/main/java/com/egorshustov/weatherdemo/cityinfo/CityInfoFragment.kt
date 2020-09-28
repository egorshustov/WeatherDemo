package com.egorshustov.weatherdemo.cityinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.egorshustov.weatherdemo.R
import com.egorshustov.weatherdemo.adapters.DailyWeatherAdapter
import com.egorshustov.weatherdemo.base.BaseFragment
import com.egorshustov.weatherdemo.databinding.FragmentCityInfoBinding
import com.egorshustov.weatherdemo.util.EventObserver
import com.egorshustov.weatherdemo.util.NetworkNotifier
import com.egorshustov.weatherdemo.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityInfoFragment : BaseFragment<CityInfoViewModel, FragmentCityInfoBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_city_info

    override val viewModel: CityInfoViewModel by viewModels()

    private val args: CityInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerDailyForecast.adapter = DailyWeatherAdapter()
        viewModel.onCityIdObtained(args.cityId)
        setObservers()
    }

    private fun setObservers() = with(viewModel) {
        message.observe(viewLifecycleOwner, EventObserver { context?.showMessage(it) })
        NetworkNotifier.networkRestoredEvent.observe(viewLifecycleOwner, EventObserver {
            context?.showMessage(getString(R.string.text_connection_restored))
            viewModel.onNetworkRestored()
        })
    }
}