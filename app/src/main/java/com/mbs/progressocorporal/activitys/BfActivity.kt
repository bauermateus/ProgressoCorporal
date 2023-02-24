package com.mbs.progressocorporal.activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.mbs.progressocorporal.databinding.ActivityBfBinding
import com.mbs.progressocorporal.fragments.BfCalculateFragment
import com.mbs.progressocorporal.viewmodels.BfViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBfBinding
    private var genero: Int? = null
    private val viewModel: BfViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBfBinding.inflate(layoutInflater)
        setContentView(binding.root)
        genero = intent.extras?.getInt(GENERO)
        viewModel.setGender(genero)
        placeImcCalculateFragment()
    }

    private fun placeImcCalculateFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, BfCalculateFragment()).commit()
    }
    companion object {

        /** 0 for women, 1 for men*/
        private const val GENERO = "sex"
        fun newInstance(genero: Int, context: Context): Intent {
            val activity = Intent(context, BfActivity::class.java)
            activity.putExtra(GENERO, genero)
            return activity
        }
    }
}