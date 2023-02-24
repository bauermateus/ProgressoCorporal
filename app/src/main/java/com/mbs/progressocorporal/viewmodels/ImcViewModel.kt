package com.mbs.progressocorporal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImcViewModel @Inject constructor() : ViewModel() {

    private val _imcResult = MutableLiveData<Float>()
    val imcResult: LiveData<Float> get() = _imcResult

    fun calculateImc(altura: Float, peso: Float) {

        viewModelScope.launch(Dispatchers.IO) {
            val result = peso / ((altura / 100) * (altura / 100))
            val final = String.format("%.1f", result)

            withContext(Dispatchers.Main) {
                _imcResult.value = final.toFloat()
            }
        }
    }
}