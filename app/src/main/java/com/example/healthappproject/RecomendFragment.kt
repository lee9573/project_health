package com.example.healthappproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.healthappproject.databinding.FragmentRecomendBinding
import com.example.healthappproject.network.CalorieNinjasApi
import com.example.healthappproject.network.CalorieNinjasResponse
import com.example.healthappproject.network.RetrofitInstance
import com.example.healthappproject.viewmodels.RecomendViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class MealDataR(
    val name: String,
    val kcal: Double,
    val grams: Double,
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double
)


class RecomendFragment : Fragment() {
    private var binding: FragmentRecomendBinding? = null

    // 뷰모델 참조
    private val recomendViewModel: RecomendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecomendBinding.inflate(inflater, container, false)

        // mealType 설정 함수 호출
        setupMealType(binding)

        // 홈 버튼 클릭 시 DietFragment로 돌아가기
        homeButtonClick(binding)

        // CalorieNinjas API 호출
        searchCalorieInfo(binding)

        // Meal 데이터 추가 버튼 클릭 시 호출되는 함수
        addMealData(binding)

        // Direct Meal 데이터 추가 버튼 클릭 시 호출되는 함수
        directAddMealData(binding)

        // Final Meal 데이터 처리 버튼 클릭 시 호출되는 함수
        finalAddMealData(binding)

        return binding.root  // Fragment의 뷰를 반환
    }

    // mealType을 설정하는 함수
    private fun setupMealType(binding: FragmentRecomendBinding) {
        val mealType = arguments?.getString("mealType")
        if (mealType != null) {
            binding.mealType.text = mealType
        } else {
            Log.e("RecomendFragment", "MealType is null")
            binding.mealType.text = "No meal type provided"
        }
    }

    // 홈 버튼 클릭 시 DietFragment로 돌아가는 함수
    private fun homeButtonClick(binding: FragmentRecomendBinding) {
        binding.home1Btn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_recomendFragment_to_dietFragment)
        }
    }

    // CalorieNinjas API 호출 함수
    private fun searchCalorieInfo(binding: FragmentRecomendBinding) {
        binding.searchBtn.setOnClickListener {
            val query = binding.editDietName.text.toString().trim()
            if (query.isEmpty()) {
                Log.e("RecomendFragment", "Query is empty")
                return@setOnClickListener
            }

            val apiKey = "DsIcgX94L5D9s+O1+LLr8g==jkQjUfIODrEQLr9Y" // CalorieNinjas API 키
            RetrofitInstance.api.getNutrition(apiKey, query).enqueue(object : Callback<CalorieNinjasResponse> {
                override fun onResponse(
                    call: Call<CalorieNinjasResponse>,
                    response: Response<CalorieNinjasResponse>
                ) {
                    if (response.isSuccessful) {
                        val items = response.body()?.items
                        if (!items.isNullOrEmpty()) {
                            val firstItem = items[0] // 첫 번째 검색 결과 사용
                            binding.kcal.setText("${firstItem.calories}")
                            binding.gram.setText("${firstItem.carbohydrates_total_g}")
                            binding.carbo.setText("${firstItem.fat_total_g}")
                            binding.protein.setText("${firstItem.protein_g}")
                            binding.fat.setText("${firstItem.fat_total_g}")
                        } else {
                            Log.e("RecomendFragment", "No items found")
                        }
                    } else {
                        Log.e("RecomendFragment", "API call failed: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<CalorieNinjasResponse>, t: Throwable) {
                    Log.e("RecomendFragment", "Error: ${t.message}")
                }
            })
        }
    }

    // Meal 데이터를 추가하는 함수
    private fun addMealData(binding: FragmentRecomendBinding) {
        binding.addNameFirBtn.setOnClickListener {

            // 입력된 값 가져오기
            val nameInput = binding.editDietName.text.toString()
            val kcalInput = binding.kcal.text.toString().toDoubleOrNull() ?: 0
            val gInput = binding.gram.text.toString().toDoubleOrNull() ?: 0
            val carboInput = binding.carbo.text.toString().toDoubleOrNull() ?: 0
            val proteinInput = binding.protein.text.toString().toDoubleOrNull() ?: 0
            val fatInput = binding.fat.text.toString().toDoubleOrNull() ?: 0


            // 빈 값 체크
            if (nameInput.isEmpty() || kcalInput == 0 || gInput == 0 || carboInput == 0 || proteinInput == 0 || fatInput == 0) {
                Log.e("RecomendFragment", "One or more inputs are empty")
                return@setOnClickListener
            }

            // MealData 객체 생성
            val mealDataR = MealDataR(
                name = nameInput,
                kcal = kcalInput.toDouble(),
                grams = gInput.toDouble(),
                carbohydrates = carboInput.toDouble(),
                protein = proteinInput.toDouble(),
                fat = fatInput.toDouble()
            )

            // ViewModel을 통해 총합 업데이트
            recomendViewModel.updateTotals(mealDataR)

            // 뷰모델의 값을 사용하여 UI 업데이트
            binding.totalKalView.text = recomendViewModel.totalKcal.toString()
        }
    }


    // Direct Meal 데이터를 추가하는 함수
    private fun directAddMealData(binding: FragmentRecomendBinding) {
        binding.directAddBtn.setOnClickListener {
            // 입력된 값 가져오기
            val nameInput = binding.nameInput.text.toString()
            val kcalInput = binding.kcalInput.text.toString().toIntOrNull() ?: 0
            val gInput = binding.gInput.text.toString().toIntOrNull() ?: 0
            val carboInput = binding.carboInput.text.toString().toIntOrNull() ?: 0
            val proteinInput = binding.proteinInput.text.toString().toIntOrNull() ?: 0
            val fatInput = binding.fatInput.text.toString().toIntOrNull() ?: 0


            // 빈 값 체크
            if (nameInput.isNullOrEmpty() || kcalInput == 0 || gInput == 0 || carboInput == 0 || proteinInput == 0 || fatInput == 0) {
                Log.e("RecomendFragment", "One or more inputs are empty")
                return@setOnClickListener
            }

            // MealData 객체 생성
            val mealDataR = MealDataR(
                name = nameInput,
                kcal = kcalInput.toDouble(),
                grams = gInput.toDouble(),
                carbohydrates = carboInput.toDouble(),
                protein = proteinInput.toDouble(),
                fat = fatInput.toDouble()
            )

            // ViewModel을 통해 총합 업데이트
            recomendViewModel.updateTotals(mealDataR)
            // totalKcal 값을 텍스트뷰에 업데이트
            binding.totalKalView.text = recomendViewModel.totalKcal.toString()
        }
    }

    // Final Meal 데이터를 처리하는 함수
    private fun finalAddMealData(binding: FragmentRecomendBinding) {
        binding.addFinalBtn.setOnClickListener {
            val mealType = arguments?.getString("mealType") ?: "Unknown"

            // 빈 값이 있는지 체크
            if (recomendViewModel.totalmeal == 0 || recomendViewModel.totalKcal == 0.0 || recomendViewModel.totalg == 0.0 ||
                recomendViewModel.totalcarbo == 0.0 || recomendViewModel.totalprotein == 0.0 || recomendViewModel.totalfat == 0.0) {
                Log.e("RecomendFragment", "One or more inputs are empty")
                return@setOnClickListener
            }

            // 값을 Bundle에 담아서 dietFragment로 넘깁니다
            val bundle = Bundle().apply {
                putString("name_input", recomendViewModel.totalmeal.toString())
                putString("kcal_input", recomendViewModel.totalKcal.toString())
                putString("g_input", recomendViewModel.totalg.toString())
                putString("carbo_input", recomendViewModel.totalcarbo.toString())
                putString("protein_input", recomendViewModel.totalprotein.toString())
                putString("fat_input", recomendViewModel.totalfat.toString())
                putString("mealType", mealType)
            }

            Navigation.findNavController(it).navigate(R.id.action_recomendFragment_to_dietFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null  // 메모리 누수를 방지하기 위해 binding을 null로 설정
    }
}
