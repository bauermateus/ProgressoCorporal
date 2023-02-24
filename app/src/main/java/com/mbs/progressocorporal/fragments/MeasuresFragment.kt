package com.mbs.progressocorporal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mbs.progressocorporal.R
import com.mbs.progressocorporal.activitys.BfActivity
import com.mbs.progressocorporal.databinding.FragmentMeasuresBinding
import com.mbs.progressocorporal.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeasuresFragment : Fragment() {

    private var _binding: FragmentMeasuresBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMeasuresBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chart.apply {
            setNoDataTextColor(ContextCompat.getColor(requireContext(), R.color.seed))
        }
        binding.buttonCalculateImc.setOnClickListener {
            findNavController().navigate(R.id.action_measuresFragment_to_imcActivity)
        }
        handleBfCalculateButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleBfCalculateButton() {
        binding.buttonCalculateBf.setOnClickListener {

            when (binding.toggleGroup.checkedButtonId) {
                R.id.men_selected_button -> {
                    startActivity(BfActivity.newInstance(1, requireContext()))
                }
                R.id.women_selected_button -> {
                    startActivity(BfActivity.newInstance(0, requireContext()))
                }
                else -> Snackbar.make(binding.root, "É preciso selecionar um gênero.", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}