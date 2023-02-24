package com.mbs.progressocorporal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.log10

@HiltViewModel
class BfViewModel @Inject constructor() : ViewModel() {

    /**0 for women, 1 for men.*/
    private val _gender: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val gender:LiveData<Int> get() = _gender

    private val _bfResult: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val bfResult: LiveData<String> get() = _bfResult

    /**Transforms the value in centimeters to inches.*/
    private fun Float.toInches(): Float {
        return this / 2.54.toFloat()
    }

    fun calculateMenBf(waist: Float, neck: Float, height: Float) {
        val result = 495 / (1.0324 - 0.19077 * log10(waist - neck) + 0.15456 * log10(height)) - 450
        _bfResult.value = String.format("%.1f", result)
    }

    fun calculateWomenBf(waist: Float, hip: Float, neck: Float, height: Float) {
        val result = 495 / (1.29579 - 0.35004 * log10(waist + hip - neck) + 0.22100 * log10(height)) - 450
        _bfResult.value = String.format("%.1f", result)
    }

    fun setGender(gender: Int?) {
        _gender.value = gender
    }
}