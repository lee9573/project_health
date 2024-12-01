package com.example.healthappproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.healthappproject.databinding.FragmentDietBinding
import androidx.navigation.fragment.findNavController
import com.example.healthappproject.viewmodels.DietViewModel


// Meal 데이터를 담을 데이터 클래스
data class MealData(
    val mealType: String,
    val kcalInput: String?,
    val carboInput: String?,
    val proteinInput: String?,
    val fatInput: String?
)

class DietFragment : Fragment() {
    private var binding: FragmentDietBinding? = null

    // ViewModel 초기화
    private val dietViewModel: DietViewModel by activityViewModels()  // Activity 공유 ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ProgressBarFragment를 FrameLayout에 추가
        addProgressBarFragment()
        binding = FragmentDietBinding.inflate(inflater, container, false)

        // 버튼 클릭 리스너 설정
        binding?.breakfastBtn?.setOnClickListener {
            navigateToRecomendFragment("breakfast")
        }

        binding?.lunchBtn?.setOnClickListener {
            navigateToRecomendFragment("lunch")
        }

        binding?.dinnerBtn?.setOnClickListener {
            navigateToRecomendFragment("dinner")
        }

        binding?.snackBtn?.setOnClickListener {
            navigateToRecomendFragment("snack")
        }

        // 전달된 값들을 받아오기
        val (mealType, kcalInput, carboInput, proteinInput, fatInput) = getMealData()

        // 값들 업데이트 (ViewModel로 로직 이동)
        dietViewModel.updateMealData(kcalInput, carboInput, proteinInput, fatInput)
        dietViewModel.updateMealDataT(mealType, kcalInput)
        // MealType에 맞는 텍스트를 가져와서 UI에 반영
        updateMealText(mealType)

        // UI 값 업데이트
        updateNutritionalValues()
        updateNutritionalValuesT()

        return binding?.root  // Fragment의 뷰를 반환
    }
    private fun addProgressBarFragment() {
        val progressBarFragment = ProgressBarFragment()

        // FrameLayout에 ProgressBarFragment를 동적으로 추가
        childFragmentManager.beginTransaction()
            .replace(R.id.progress_bar_Frame, progressBarFragment)
            .commit()
    }

    // 공통 네비게이션 함수
    private fun navigateToRecomendFragment(mealType: String) {
        // 전달할 데이터인 mealType을 인자로 전달하면서 RecomendFragment로 이동
        val action = DietFragmentDirections.actionDietFragmentToRecomendFragment(mealType)
        findNavController().navigate(action)  // findNavController 호출
    }

    // 전달된 값들을 가져오는 함수
    private fun getMealData(): MealData {
        val mealType = arguments?.getString("mealType") ?: "Unknown"
        val kcalInput = arguments?.getString("kcal_input")
        val carboInput = arguments?.getString("carbo_input")
        val proteinInput = arguments?.getString("protein_input")
        val fatInput = arguments?.getString("fat_input")
        return MealData(mealType, kcalInput, carboInput, proteinInput, fatInput)
    }
    // MealType에 맞는 텍스트를 업데이트하는 함수
    private fun updateMealTextT(mealType: String) {
        val mealText = dietViewModel.getMealTextT(mealType)

        when (mealType) {
            "breakfast" -> binding?.breakfast?.text = mealText
            "lunch" -> binding?.lunch?.text = mealText
            "dinner" -> binding?.dinner?.text = mealText
            "snack" -> binding?.snack?.text = mealText
            else -> Log.e("DietFragment", "Unknown meal type: $mealType")
        }
    }
    // 영양 정보 업데이트 함수
    private fun updateNutritionalValuesT() {
        val nutritionalValues = dietViewModel.getNutritionalValuesT()

        // UI 업데이트
        binding?.apply {
            breakfast.text = "${nutritionalValues["breakfast"]}"
            lunch.text = "${nutritionalValues["lunch"]}"
            dinner.text = "${nutritionalValues["dinner"]}"
            snack.text = "${nutritionalValues["snack"]}"
        }
    }


    // 영양 정보 업데이트 함수
    private fun updateNutritionalValues() {
        // ViewModel에서 계산된 최신 데이터 가져오기
        val values = dietViewModel.getUpdatedValues()
        val progressValues = dietViewModel.getProgressData()

        binding?.apply {
            //perDis.text = "${values["perd"]} %"
            totalKcal.text = "${values["totalKcal"]} Kcal"
            eatKcal.text = "eat ${values["eatKcal"]} Kcal"
            remainKcal.text = "remain ${values["remainKcal"]} Kcal"
            eatCarbo.text = "eat ${values["eatCarbo"]} G"
            remainCarbo.text = "remain ${values["remainCarbo"]} G"
            eatProtein.text = "eat ${values["eatProtein"]} G"
            remainProtein.text = "remain ${values["remainProtein"]} G"
            eatFat.text = "eat ${values["eatFat"]} G"
            remainFat.text = "remain ${values["remainFat"]} G"
        }

        // ProgressBar 설정
        binding?.apply {
            //progressKcal.progress = (progressValues["progressKcal"] ?: 0.0).toInt()
            progressCarbo.progress = (progressValues["progressCarbo"] ?: 0.0).toInt()
            progressProtein.progress = (progressValues["progressProtein"] ?:0).toInt()
            progressFat.progress = (progressValues["progressFat"] ?: 0).toInt()
        }
    }
    // MealType에 맞는 텍스트를 업데이트하는 함수
    private fun updateMealText(mealType: String) {
        val mealText = dietViewModel.getMealTextT(mealType)

        when (mealType) {
            "breakfast" -> binding?.breakfast?.text = mealText
            "lunch" -> binding?.lunch?.text = mealText
            "dinner" -> binding?.dinner?.text = mealText
            "snack" -> binding?.snack?.text = mealText
            else -> Log.e("DietFragment", "Unknown meal type: $mealType")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null  // ViewBinding 해제
    }
}
