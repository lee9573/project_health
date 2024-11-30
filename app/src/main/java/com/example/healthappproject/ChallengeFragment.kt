package com.example.healthappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthappproject.databinding.FragmentChallengeBinding
import com.example.healthappproject.viewmodels.ChallengeViewModel

class ChallengeFragment : Fragment() {

    private var _binding: FragmentChallengeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChallengeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChallengeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 및 ViewPager2 초기화
        setupRecyclerView()
        setupViewPager()

        // ViewModel 데이터 관찰
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.challengesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ChallengeAdapter(emptyList()) { isChecked ->
            viewModel.updateCompletedChallenges(isChecked) //체크된 상태를 ViewModel에 전달
        }
        binding.challengesRecyclerView.adapter = adapter

        viewModel.challenges.observe(viewLifecycleOwner) { challenges ->
            adapter.updateChallenges(challenges)
        }
    }

    private fun setupViewPager() {
        val adapter = YoutubeBannerAdapter(viewModel.videoIds,viewModel.videoUrls)
        binding.youtubeBannerPager.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.completedChallenges.observe(viewLifecycleOwner) { completed ->
            updateProgressBar(completed)
        }
    }

    private fun updateProgressBar(completed: Int) {
        val progress = ((completed / ChallengeViewModel.TOTAL_CHALLENGES.toFloat()) * 100).toInt()
        binding.challengeProgress.progress = progress

        if (completed == ChallengeViewModel.TOTAL_CHALLENGES) {
            Toast.makeText(requireContext(), "All Challenges Completed!", Toast.LENGTH_LONG).show()
            viewModel.resetChallenges() // 챌린지 초기화
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
