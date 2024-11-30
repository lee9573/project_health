package com.example.healthappproject.network


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// CalorieNinjas API 인터페이스
interface CalorieNinjasApi {
    @GET("v1/nutrition")
    fun getNutrition(
        @Header("X-Api-Key") apiKey: String, // API 키
        @Query("query") query: String        // 검색할 식단 이름
    ): Call<CalorieNinjasResponse>
}
