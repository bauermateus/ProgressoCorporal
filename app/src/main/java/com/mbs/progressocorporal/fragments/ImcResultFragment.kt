package com.mbs.progressocorporal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mbs.progressocorporal.R
import com.mbs.progressocorporal.databinding.FragmentImcResultBinding
import com.mbs.progressocorporal.viewmodels.ImcViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.absoluteValue

@AndroidEntryPoint
class ImcResultFragment : Fragment() {
    private var _binding: FragmentImcResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImcViewModel by activityViewModels()
    var resultado: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentImcResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setObserver() {
        viewModel.imcResult.observe(viewLifecycleOwner){ data ->
            resultado = data
            handleImcResult()
        }
    }

    fun handleImcResult() {
        binding.imcValue.text = resultado.toString()
        when {
            resultado in 0.0..18.4 -> {
                binding.imcQualidade.text = "Abaixo do peso"
                binding.imcQualidade.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blur_yellow
                    )
                )
            }
            resultado in 18.5..24.9 -> {
                binding.imcQualidade.text = "Peso normal"
                binding.imcQualidade.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.seed
                    )
                )
            }
            resultado in 25.0..29.9 -> {
                binding.imcQualidade.text = "Sobrepeso"
                binding.imcQualidade.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blur_yellow
                    )
                )
            }
            resultado in 30.0..34.9 -> {
                binding.imcQualidade.text = "Obesidade grau 1"
                binding.imcQualidade.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blur_yellow
                    )
                )
            }
            resultado in 35.0..39.9 -> {
                binding.imcQualidade.text = "Obesidade grau 2"
                binding.imcQualidade.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blur_red
                    )
                )
            }
            resultado > 40.0 -> {
                binding.imcQualidade.text = "Obesidade grau 3"
                binding.imcQualidade.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blur_red
                    )
                )
            }
            else -> binding.imcQualidade.text = "Ops, algum erro ocorreu!"
        }
    }
}