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
import com.google.firebase.database.* // Firebase Realtime Database


// MealDataR 데이터 클래스를 사용해 Meal 정보를 나타냅니다.
data class MealDataR(
    val name: String,
    val kcal: Double,
    val grams: Double,
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double
)


class RecomendFragment : Fragment() {
    private var binding: FragmentRecomendBinding? = null  // Fragment의 ViewBinding 객체
    private val firebaseDatabase = FirebaseDatabase.getInstance()  // Firebase Database 인스턴스 생성

    // 뷰모델 참조: ViewModel을 사용해 UI 관련 데이터 처리
    private val recomendViewModel: RecomendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecomendBinding.inflate(inflater, container, false)  // Fragment에 ViewBinding 설정

        // mealType을 설정하는 함수 호출
        setupMealType(binding)

        // 홈 버튼 클릭 시 DietFragment로 돌아가는 함수 설정
        homeButtonClick(binding)

        // CalorieNinjas API를 호출해 칼로리 정보를 가져오는 함수 설정
        searchCalorieInfo(binding)

        // Meal 데이터를 추가하는 버튼에 대한 클릭 리스너 설정
        addMealData(binding)

        // Direct Meal 데이터를 추가하는 버튼에 대한 클릭 리스너 설정
        directAddMealData(binding)

        // Final Meal 데이터 처리 버튼 클릭 리스너 설정
        finalAddMealData(binding)

        // Realtime Database에서 추천 정보를 가져오는 버튼 클릭 리스너 설정
        binding.recomendBtn.setOnClickListener {
            fetchRecommendationFromFirebase(binding)
        }

        return binding.root  // Fragment의 루트 뷰 반환
    }

    // Firebase에서 추천 데이터를 가져오는 함수
    private fun fetchRecommendationFromFirebase(binding: FragmentRecomendBinding) {
        val databaseUrl = "https://recomenddiet-default-rtdb.firebaseio.com/"
        val databaseRef = FirebaseDatabase.getInstance(databaseUrl).getReference("recommendations/breakfast")

        // Firebase Database에서 데이터를 읽어오는 리스너 설정
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Firebase에서 읽어온 데이터를 변수에 저장
                    val name = snapshot.child("name").getValue(String::class.java) ?: "N/A"
                    val kcal = snapshot.child("kcal").getValue(Double::class.java) ?: 0.0
                    val carbohydrates = snapshot.child("carbohydrates").getValue(Double::class.java) ?: 0.0
                    val protein = snapshot.child("protein").getValue(Double::class.java) ?: 0.0
                    val fat = snapshot.child("fat").getValue(Double::class.java) ?: 0.0
                    Log.d("RecomendFragment", "Name: $name, Kcal: $kcal, Carbo: $carbohydrates, Protein: $protein, Fat: $fat")

                    // UI 업데이트
                    binding?.apply {
                        bNameReTxt.text = "a"// name
                        bKcalReTxt.text = "a"//String.format("%.1f Kcal", kcal)
                        bCarboReTxt.text ="a"// String.format("%.1f G", carbohydrates)
                        bProteinReTxt.text ="a"// String.format("%.1f G", protein)
                        bFatReTxt.text ="a"// String.format("%.1f G", fat)
                    }
                } else {
                    Log.e("Firebase", "Data not found")  // Firebase에서 데이터가 없을 경우
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Failed to read data: ${error.message}")  // Firebase 데이터 읽기 실패 시 에러 로그 출력
            }
        })
    }

    // MealType 설정 함수: MealType을 Fragment에 전달된 argument로 설정
    private fun setupMealType(binding: FragmentRecomendBinding) {
        val mealType = arguments?.getString("mealType")
        if (mealType != null) {
            binding.mealType.text = mealType
        } else {
            Log.e("RecomendFragment", "MealType is null")  // MealType이 null일 경우 로그 출력
            binding.mealType.text = "No meal type provided"
        }
    }

    // 홈 버튼 클릭 시 DietFragment로 돌아가는 함수
    private fun homeButtonClick(binding: FragmentRecomendBinding) {
        binding.home1Btn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_recomendFragment_to_dietFragment)  // 홈 버튼 클릭 시 DietFragment로 이동
        }
    }

    // CalorieNinjas API 호출 함수: 사용자가 입력한 식품 이름으로 칼로리 정보를 조회
    private fun searchCalorieInfo(binding: FragmentRecomendBinding) {
        binding.searchBtn.setOnClickListener {
            val query = binding.editDietName.text.toString().trim()  // 사용자가 입력한 식품 이름
            if (query.isEmpty()) {
                Log.e("RecomendFragment", "Query is empty")  // 입력값이 비었을 경우 에러 로그 출력
                return@setOnClickListener
            }

            val apiKey = "DsIcgX94L5D9s+O1+LLr8g==jkQjUfIODrEQLr9Y"  // CalorieNinjas API 키
            RetrofitInstance.api.getNutrition(apiKey, query).enqueue(object : Callback<CalorieNinjasResponse> {
                override fun onResponse(
                    call: Call<CalorieNinjasResponse>,
                    response: Response<CalorieNinjasResponse>
                ) {
                    if (response.isSuccessful) {
                        val items = response.body()?.items
                        if (!items.isNullOrEmpty()) {
                            // 첫 번째 검색 결과를 사용하여 정보를 UI에 반영
                            val firstItem = items[0]
                            binding.kcal.setText("${firstItem.calories}")
                            binding.gram.setText("${firstItem.carbohydrates_total_g}")
                            binding.carbo.setText("${firstItem.fat_total_g}")
                            binding.protein.setText("${firstItem.protein_g}")
                            binding.fat.setText("${firstItem.fat_total_g}")
                        } else {
                            Log.e("RecomendFragment", "No items found")  // 검색 결과가 없을 경우 에러 로그 출력
                        }
                    } else {
                        Log.e("RecomendFragment", "API call failed: ${response.message()}")  // API 호출 실패 시 에러 로그 출력
                    }
                }

                override fun onFailure(call: Call<CalorieNinjasResponse>, t: Throwable) {
                    Log.e("RecomendFragment", "Error: ${t.message}")  // 네트워크 오류 시 에러 로그 출력
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

            // 값이 비었거나 0일 경우 로그 출력 후 함수 종료
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

            // ViewModel의 값을 사용하여 UI 업데이트
            binding.totalKalView.text = recomendViewModel.totalKcal.toString()
        }
    }

    // Direct Meal 데이터를 추가하는 함수 (사용자 직접 입력)
    private fun directAddMealData(binding: FragmentRecomendBinding) {
        binding.directAddBtn.setOnClickListener {
            // 입력된 값 가져오기
            val nameInput = binding.nameInput.text.toString()
            val kcalInput = binding.kcalInput.text.toString().toIntOrNull() ?: 0
            val gInput = binding.gInput.text.toString().toIntOrNull() ?: 0
            val carboInput = binding.carboInput.text.toString().toIntOrNull() ?: 0
            val proteinInput = binding.proteinInput.text.toString().toIntOrNull() ?: 0
            val fatInput = binding.fatInput.text.toString().toIntOrNull() ?: 0

            // 값이 비었거나 0일 경우 로그 출력 후 함수 종료
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
            // UI에 totalKcal 값 업데이트
            binding.totalKalView.text = recomendViewModel.totalKcal.toString()
        }
    }

    // Final Meal 데이터를 처리하는 함수: 최종 Meal 데이터 처리
    private fun finalAddMealData(binding: FragmentRecomendBinding) {
        binding.addFinalBtn.setOnClickListener {
            val mealType = arguments?.getString("mealType") ?: "Unknown"

            // 빈 값이 있는지 체크
            if (recomendViewModel.totalmeal == 0 || recomendViewModel.totalKcal == 0.0 || recomendViewModel.totalg == 0.0 ||
                recomendViewModel.totalcarbo == 0.0 || recomendViewModel.totalprotein == 0.0 || recomendViewModel.totalfat == 0.0) {
                Log.e("RecomendFragment", "One or more inputs are empty")
                return@setOnClickListener
            }

            // 값을 Bundle에 담아서 dietFragment로 넘김
            val bundle = Bundle().apply {
                putString("name_input", recomendViewModel.totalmeal.toString())
                putString("kcal_input", recomendViewModel.totalKcal.toString())
                putString("g_input", recomendViewModel.totalg.toString())
                putString("carbo_input", recomendViewModel.totalcarbo.toString())
                putString("protein_input", recomendViewModel.totalprotein.toString())
                putString("fat_input", recomendViewModel.totalfat.toString())
                putString("mealType", mealType)
            }

            // DietFragment로 이동
            Navigation.findNavController(it).navigate(R.id.action_recomendFragment_to_dietFragment, bundle)
        }
    }

    // Fragment의 뷰가 파괴될 때 호출되어 메모리 누수를 방지하기 위해 binding을 null로 설정
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
