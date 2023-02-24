package com.mbs.progressocorporal.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mbs.progressocorporal.fragments.ImcCalculateFragment
import com.mbs.progressocorporal.databinding.ActivityImcBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        placeImcCalculateFragment()
    }

    private fun placeImcCalculateFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, ImcCalculateFragment()).commit()
    }
}