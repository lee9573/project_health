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
    val mealType: String,       // 식사 유형 (ex. breakfast, lunch, dinner, snack)
    val kcalInput: String?,     // 칼로리 정보
    val carboInput: String?,    // 탄수화물 정보
    val proteinInput: String?,  // 단백질 정보
    val fatInput: String?       // 지방 정보
)

class DietFragment : Fragment() {
    private var binding: FragmentDietBinding? = null

    // ViewModel 초기화 (Activity 범위의 ViewModel 공유)
    private val dietViewModel: DietViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ProgressBarFragment를 Fragment 내부의 FrameLayout에 추가
        addProgressBarFragment()

        // ViewBinding을 초기화하여 뷰 참조 설정
        binding = FragmentDietBinding.inflate(inflater, container, false)

        // 각 버튼에 대한 클릭 리스너 설정
        binding?.breakfastBtn?.setOnClickListener {
            navigateToRecomendFragment("breakfast") // 아침 메뉴로 이동
        }
        binding?.lunchBtn?.setOnClickListener {
            navigateToRecomendFragment("lunch") // 점심 메뉴로 이동
        }
        binding?.dinnerBtn?.setOnClickListener {
            navigateToRecomendFragment("dinner") // 저녁 메뉴로 이동
        }
        binding?.snackBtn?.setOnClickListener {
            navigateToRecomendFragment("snack") // 간식 메뉴로 이동
        }

        // 전달받은 데이터 가져오기
        val (mealType, kcalInput, carboInput, proteinInput, fatInput) = getMealData()

        // ViewModel에 데이터를 업데이트
        dietViewModel.updateMealData(kcalInput, carboInput, proteinInput, fatInput)
        dietViewModel.updateMealDataT(mealType, kcalInput)

        // MealType에 따라 텍스트 업데이트
        updateMealText(mealType)

        // UI 업데이트
        updateNutritionalValues()
        updateNutritionalValuesT()

        return binding?.root // Fragment의 루트 뷰 반환
    }

    // ProgressBarFragment를 추가하는 함수
    private fun addProgressBarFragment() {
        val progressBarFragment = ProgressBarFragment()
        // FrameLayout에 ProgressBarFragment를 추가
        childFragmentManager.beginTransaction()
            .replace(R.id.progress_bar_Frame, progressBarFragment)
            .commit()
    }

    // 특정 mealType에 따라 RecomendFragment로 이동
    private fun navigateToRecomendFragment(mealType: String) {
        // mealType 데이터를 전달하며 RecomendFragment로 이동
        val action = DietFragmentDirections.actionDietFragmentToRecomendFragment(mealType)
        findNavController().navigate(action) // Navigation을 통해 이동
    }

    // 전달받은 데이터를 MealData로 반환
    private fun getMealData(): MealData {
        val mealType = arguments?.getString("mealType") ?: "Unknown"
        val kcalInput = arguments?.getString("kcal_input")
        val carboInput = arguments?.getString("carbo_input")
        val proteinInput = arguments?.getString("protein_input")
        val fatInput = arguments?.getString("fat_input")
        return MealData(mealType, kcalInput, carboInput, proteinInput, fatInput)
    }

    // MealType에 따른 텍스트 업데이트 함수
    private fun updateMealText(mealType: String) {
        val mealText = dietViewModel.getMealTextT(mealType) // ViewModel에서 텍스트 가져오기

        // mealType에 맞는 UI 업데이트
        when (mealType) {
            "breakfast" -> binding?.breakfast?.text = mealText
            "lunch" -> binding?.lunch?.text = mealText
            "dinner" -> binding?.dinner?.text = mealText
            "snack" -> binding?.snack?.text = mealText
            else -> Log.e("DietFragment", "Unknown meal type: $mealType") // 잘못된 mealType 로그 출력
        }
    }

    // 전체 영양 정보 업데이트
    private fun updateNutritionalValues() {
        val values = dietViewModel.getUpdatedValues() // 최신 데이터 가져오기
        val progressValues = dietViewModel.getProgressData() // ProgressBar 데이터 가져오기

        binding?.apply {
            // 전체 칼로리 정보와 각 영양소 정보 업데이트
            totalKcal.text = "${values["totalKcal"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} Kcal"
            eatKcal.text = "eat ${values["eatKcal"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} Kcal"
            remainKcal.text = "remain ${values["remainKcal"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} Kcal"
            eatCarbo.text = "eat ${values["eatCarbo"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} G"
            remainCarbo.text = "remain ${values["remainCarbo"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} G"
            eatProtein.text = "eat ${values["eatProtein"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} G"
            remainProtein.text = "remain ${values["remainProtein"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} G"
            eatFat.text = "eat ${values["eatFat"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} G"
            remainFat.text = "remain ${values["remainFat"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0} G"

            // ProgressBar의 진행률 업데이트
            progressCarbo.progress = (progressValues["progressCarbo"] ?: 0.0).toInt()
            progressProtein.progress = (progressValues["progressProtein"] ?: 0).toInt()
            progressFat.progress = (progressValues["progressFat"] ?: 0).toInt()
        }
    }

    // 각 MealType에 따른 영양 정보 업데이트
    private fun updateNutritionalValuesT() {
        val nutritionalValues = dietViewModel.getNutritionalValuesT()

        binding?.apply {
            breakfast.text = "${nutritionalValues["breakfast"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0}"
            lunch.text = "${nutritionalValues["lunch"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0}"
            dinner.text = "${nutritionalValues["dinner"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0}"
            snack.text = "${nutritionalValues["snack"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0}"
        }
    }

    // Fragment의 뷰가 파괴될 때 호출
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // 메모리 누수를 방지하기 위해 binding 해제
    }
}
