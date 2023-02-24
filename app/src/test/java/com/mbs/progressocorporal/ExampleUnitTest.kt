package com.mbs.progressocorporal

import com.mbs.progressocorporal.viewmodels.BfViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.math.log10

class ExampleUnitTest {
    fun calculateWomenBf(waist: Float, hip: Float, neck: Float, height: Float): Float {
        val result = 163.205 * log10((waist.toInches() + hip.toInches() - neck.toInches()))
        -97.684 * log10(height.toInches()) - 78.387
        return String.format("%.1f", (result / 10)).replace(",", ".").toFloat()
    }
    private fun Float.toInches(): Float {
        return this / 2.54.toFloat()
    }

    @Before
    fun setUp() {

    }

    @Test
    fun testCalculateBodyFatPercentage() {


        val result = calculateWomenBf(70.0f, 88.0f, 34.0f, 165.0f)


        assertEquals(34.0, result)
    }
}