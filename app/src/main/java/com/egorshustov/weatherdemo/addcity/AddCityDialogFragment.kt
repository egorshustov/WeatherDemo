package com.egorshustov.weatherdemo.addcity

import androidx.fragment.app.activityViewModels
import com.egorshustov.weatherdemo.R
import com.egorshustov.weatherdemo.base.BaseDialogFragment
import com.egorshustov.weatherdemo.databinding.DialogAddCityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCityDialogFragment : BaseDialogFragment<AddCityViewModel, DialogAddCityBinding>() {

    override fun getLayoutResId(): Int = R.layout.dialog_add_city

    override val viewModel: AddCityViewModel by activityViewModels()
}