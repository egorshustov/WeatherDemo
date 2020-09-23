package com.egorshustov.weatherdemo.cityinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.egorshustov.weatherdemo.R
import com.egorshustov.weatherdemo.base.BaseFragment
import com.egorshustov.weatherdemo.databinding.FragmentCityInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityInfoFragment : BaseFragment<CityInfoViewModel, FragmentCityInfoBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_city_info

    override val viewModel: CityInfoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}