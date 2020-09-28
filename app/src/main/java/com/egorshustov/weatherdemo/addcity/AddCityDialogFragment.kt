package com.egorshustov.weatherdemo.addcity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import com.egorshustov.weatherdemo.R
import com.egorshustov.weatherdemo.base.BaseDialogFragment
import com.egorshustov.weatherdemo.data.source.remote.Result
import com.egorshustov.weatherdemo.databinding.DialogAddCityBinding
import com.egorshustov.weatherdemo.util.EventObserver
import com.egorshustov.weatherdemo.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCityDialogFragment : BaseDialogFragment<AddCityViewModel, DialogAddCityBinding>() {

    override fun getLayoutResId(): Int = R.layout.dialog_add_city

    override val viewModel: AddCityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() = with(binding) {
        editCityName.apply {
            requestFocus()
            setSelection(0)
            setOnEditorActionListener { view, _, keyEvent ->
                if (keyEvent == null || keyEvent.action == EditorInfo.IME_ACTION_DONE) {
                    val cityName = view?.text?.toString()?.trim()
                    if (cityName.isNullOrEmpty()) {
                        textInputLayout.isErrorEnabled = true
                        textInputLayout.error = "Название города не может быть пустым!"
                    } else {
                        viewModel.onCityEntered(cityName)
                    }
                    true
                } else {
                    false
                }
            }
            doOnTextChanged { text, _, _, _ ->
                textInputLayout.isErrorEnabled = false
                textInputLayout.error = ""
                val cityName = text?.toString()?.trim()
                buttonOk.isEnabled = !cityName.isNullOrEmpty()
            }
        }
        buttonOk.setOnClickListener {
            viewModel.onCityEntered(editCityName.text?.trim().toString())
        }
        buttonCancel.setOnClickListener { dismiss() }
    }

    private fun setObservers() = with(viewModel) {
        requestResult.observe(viewLifecycleOwner, EventObserver { requestResult ->
            when (requestResult) {
                is Result.Success -> {
                    context?.showMessage("Город успешно добавлен")
                    dismiss()
                }
                is Result.Error -> {
                    binding.textInputLayout.isErrorEnabled = true
                    binding.textInputLayout.error = requestResult.getString()
                }
            }
        })
    }
}