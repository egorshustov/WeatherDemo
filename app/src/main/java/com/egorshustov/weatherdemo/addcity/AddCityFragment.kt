package com.egorshustov.weatherdemo.addcity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.egorshustov.weatherdemo.R
import com.egorshustov.weatherdemo.base.BaseFragment
import com.egorshustov.weatherdemo.databinding.FragmentAddCityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCityFragment : BaseFragment<AddCityViewModel, FragmentAddCityBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_add_city

    override val viewModel: AddCityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}