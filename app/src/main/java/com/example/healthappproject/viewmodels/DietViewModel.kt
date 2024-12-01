package com.example.healthappproject.viewmodels

import androidx.lifecycle.ViewModel

class DietViewModel : ViewModel() {
    var totalKcald = 2000.0
    var remainKcald = totalKcald
    var eatKcald = 0.0
    var totalCarbod = 300.0
    var remainCarbod = totalCarbod
    var eatCarbod = 0.0
    var totalProteind = 200.0
    var remainProteind = totalProteind
    var eatProteind = 0.0
    var totalFatd = 50.0
    var remainFatd = totalFatd
    var eatFatd = 0.0

    var breakfastd = 0.0
    var lunchd = 0.0
    var dinnerd = 0.0
    var snackd = 0.0

    var perd = 0.0

    // Meal 데이터를 업데이트하는 함수
    fun updateMealData(
        kcalInput: String?,
        carboInput: String?,
        proteinInput: String?,
        fatInput: String?
    ) {
        // 영양 정보 갱신
        remainKcald -= kcalInput?.toDoubleOrNull() ?: 0.0
        remainCarbod -= carboInput?.toDoubleOrNull() ?: 0.0
        remainProteind -= proteinInput?.toDoubleOrNull() ?: 0.0
        remainFatd -= fatInput?.toDoubleOrNull() ?: 0.0

        eatKcald += kcalInput?.toDoubleOrNull() ?: 0.0
        eatCarbod += carboInput?.toDoubleOrNull() ?: 0.0
        eatProteind += proteinInput?.toDoubleOrNull() ?: 0.0
        eatFatd += fatInput?.toDoubleOrNull() ?: 0.0

        // 퍼센트 계산
        perd = ((eatKcald.toDouble() / totalKcald.toDouble()) * 100).toDouble()
    }




    // MealType에 맞는 식사 칼로리 값을 갱신
    fun updateMealDataT(mealType: String, kcalInput: String?) {
        val kcal = kcalInput?.toDoubleOrNull() ?: 0.0
        when (mealType) {
            "breakfast" -> breakfastd += kcal
            "lunch" -> lunchd += kcal
            "dinner" -> dinnerd += kcal
            "snack" -> snackd += kcal
        }
    }

    // MealType에 맞는 텍스트 값을 반환하는 함수
    fun getMealTextT(mealType: String): String {
        return when (mealType) {
            "breakfast" -> "$breakfastd"
            "lunch" -> "$lunchd"
            "dinner" -> "$dinnerd"
            "snack" -> "$snackd"
            else -> "0"
        }
    }
    // 영양 정보 값들을 반환하는 함수 (UI에 출력할 데이터 제공)
    fun getNutritionalValuesT(): Map<String, Double> {
        return mapOf(
            "breakfast" to breakfastd,
            "lunch" to lunchd,
            "dinner" to dinnerd,
            "snack" to snackd
        )
    }

    // UI에 필요한 데이터를 반환하는 함수
    fun getUpdatedValues(): Map<String, Any> {
        return mapOf(
            "perd" to perd,
            "totalKcal" to totalKcald,
            "eatKcal" to eatKcald,
            "remainKcal" to remainKcald,
            "eatCarbo" to eatCarbod,
            "remainCarbo" to remainCarbod,
            "eatProtein" to eatProteind,
            "remainProtein" to remainProteind,
            "eatFat" to eatFatd,
            "remainFat" to remainFatd,
            "breakfast" to breakfastd,
            "lunch" to lunchd,
            "dinner" to dinnerd,
            "snack" to snackd
        )
    }

    // ProgressBar에 대한 데이터 처리 (업데이트)
    fun getProgressData(): Map<String, Double> {
        return mapOf(
            "progressKcal" to perd,
            "progressCarbo" to eatCarbod,
            "progressProtein" to eatProteind,
            "progressFat" to eatFatd
        )
    }
}
