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

<<<<<<< HEAD

// Meal 데이터를 담을 데이터 클래스
data class MealData(
    val mealType: String,
    val kcalInput: String?,
    val carboInput: String?,
    val proteinInput: String?,
    val fatInput: String?
=======
// Meal 데이터를 담을 데이터 클래스
data class MealData(
    val mealType: String,       // 식사 유형 (ex. breakfast, lunch, dinner, snack)
    val kcalInput: String?,     // 칼로리 정보
    val carboInput: String?,    // 탄수화물 정보
    val proteinInput: String?,  // 단백질 정보
    val fatInput: String?       // 지방 정보
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
)

class DietFragment : Fragment() {
    private var binding: FragmentDietBinding? = null

<<<<<<< HEAD
    // ViewModel 초기화
    private val dietViewModel: DietViewModel by activityViewModels()  // Activity 공유 ViewModel
=======
    // ViewModel 초기화 (Activity 범위의 ViewModel 공유)
    private val dietViewModel: DietViewModel by activityViewModels()
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
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
=======
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
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
        childFragmentManager.beginTransaction()
            .replace(R.id.progress_bar_Frame, progressBarFragment)
            .commit()
    }

<<<<<<< HEAD
    // 공통 네비게이션 함수
    private fun navigateToRecomendFragment(mealType: String) {
        // 전달할 데이터인 mealType을 인자로 전달하면서 RecomendFragment로 이동
        val action = DietFragmentDirections.actionDietFragmentToRecomendFragment(mealType)
        findNavController().navigate(action)  // findNavController 호출
    }

    // 전달된 값들을 가져오는 함수
=======
    // 특정 mealType에 따라 RecomendFragment로 이동
    private fun navigateToRecomendFragment(mealType: String) {
        // mealType 데이터를 전달하며 RecomendFragment로 이동
        val action = DietFragmentDirections.actionDietFragmentToRecomendFragment(mealType)
        findNavController().navigate(action) // Navigation을 통해 이동
    }

    // 전달받은 데이터를 MealData로 반환
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
    private fun getMealData(): MealData {
        val mealType = arguments?.getString("mealType") ?: "Unknown"
        val kcalInput = arguments?.getString("kcal_input")
        val carboInput = arguments?.getString("carbo_input")
        val proteinInput = arguments?.getString("protein_input")
        val fatInput = arguments?.getString("fat_input")
        return MealData(mealType, kcalInput, carboInput, proteinInput, fatInput)
    }
<<<<<<< HEAD
    // MealType에 맞는 텍스트를 업데이트하는 함수
    private fun updateMealTextT(mealType: String) {
        val mealText = dietViewModel.getMealTextT(mealType)

=======

    // MealType에 따른 텍스트 업데이트 함수
    private fun updateMealText(mealType: String) {
        val mealText = dietViewModel.getMealTextT(mealType) // ViewModel에서 텍스트 가져오기

        // mealType에 맞는 UI 업데이트
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
        when (mealType) {
            "breakfast" -> binding?.breakfast?.text = mealText
            "lunch" -> binding?.lunch?.text = mealText
            "dinner" -> binding?.dinner?.text = mealText
            "snack" -> binding?.snack?.text = mealText
<<<<<<< HEAD
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
=======
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
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
    }
}
