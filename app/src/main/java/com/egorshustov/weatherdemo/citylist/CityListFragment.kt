package com.egorshustov.weatherdemo.citylist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.egorshustov.weatherdemo.R
import com.egorshustov.weatherdemo.adapters.CityAndCurrentWeatherAdapter
import com.egorshustov.weatherdemo.base.BaseFragment
import com.egorshustov.weatherdemo.databinding.FragmentCityListBinding
import com.egorshustov.weatherdemo.util.EventObserver
import com.egorshustov.weatherdemo.util.NetworkNotifier
import com.egorshustov.weatherdemo.util.safeNavigate
import com.egorshustov.weatherdemo.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityListFragment : BaseFragment<CityListViewModel, FragmentCityListBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_city_list

    override val viewModel: CityListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCitiesAndCurrentWeather.adapter = CityAndCurrentWeatherAdapter(viewModel)
        setListeners()
        setObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onCityListFragmentStarted()
    }

    private fun setListeners() {
        binding.fabAddCity.setOnClickListener {
            findNavController().safeNavigate(
                CityListFragmentDirections.actionCityListFragmentToAddCityDialogFragment()
            )
        }
    }

    private fun setObservers() = with(viewModel) {
        message.observe(viewLifecycleOwner, EventObserver { context?.showMessage(it) })
        openCityInfoCommand.observe(viewLifecycleOwner, EventObserver { cityId ->
            findNavController().safeNavigate(
                CityListFragmentDirections.actionCityListFragmentToCityInfoFragment(cityId)
            )
        })
        NetworkNotifier.networkRestoredEvent.observe(viewLifecycleOwner, EventObserver {
            context?.showMessage(getString(R.string.text_connection_restored))
            viewModel.onNetworkRestored()
        })
    }
}