package com.example.healthappproject.network

// API 응답 데이터 모델
data class CalorieNinjasResponse(
    val items: List<NutritionItem>
)

data class NutritionItem(
    val name: String,
    val calories: Double,
    val carbohydrates_total_g: Double,
    val fat_total_g: Double,
    val protein_g: Double
)