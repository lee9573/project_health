package com.example.healthappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.healthappproject.databinding.FragmentProgressbarBinding
import com.example.healthappproject.viewmodels.DietViewModel

class ProgressBarFragment : Fragment() {

    private var binding: FragmentProgressbarBinding? = null

    // DietViewModel을 Activity 범위에서 공유
    private val dietViewModel: DietViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgressbarBinding.inflate(inflater, container, false)

        // ViewModel에서 perd 값을 관찰하여 ProgressBar와 TextView 갱신
        var value = dietViewModel.perd
        updateProgressBar(value)

        return binding!!.root
    }

    // ProgressBar와 TextView 업데이트
    private fun updateProgressBar(perdValue: Double) {

        binding?.apply {
            progressKcal.progress = perdValue.toInt()
            perDis.text = "${perdValue.toInt()} %"

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
