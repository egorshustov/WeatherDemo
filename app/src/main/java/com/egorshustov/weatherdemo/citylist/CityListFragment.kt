package com.egorshustov.weatherdemo.citylist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.egorshustov.weatherdemo.R
import com.egorshustov.weatherdemo.base.BaseFragment
import com.egorshustov.weatherdemo.databinding.FragmentCityListBinding
import com.egorshustov.weatherdemo.util.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityListFragment : BaseFragment<CityListViewModel, FragmentCityListBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_city_list

    override val viewModel: CityListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.fabAddCity.setOnClickListener {
            findNavController().safeNavigate(
                CityListFragmentDirections.actionCityListFragmentToAddCityFragment()
            )
        }
    }
}