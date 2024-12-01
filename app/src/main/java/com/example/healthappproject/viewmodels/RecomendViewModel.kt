package com.example.healthappproject.viewmodels

import androidx.lifecycle.ViewModel
import com.example.healthappproject.MealDataR

class RecomendViewModel : ViewModel() {

    // 총합을 관리하는 변수들
    var totalKcal = 0.0
    var totalg = 0.0
    var totalcarbo = 0.0
    var totalprotein = 0.0
    var totalfat = 0.0
    var totalmeal = 0

    // 총합 업데이트 함수

    fun updateTotals(mealDataR: MealDataR) {
        totalmeal++
        totalKcal += mealDataR.kcal
        totalg += mealDataR.grams
        totalcarbo += mealDataR.carbohydrates
        totalprotein += mealDataR.protein
        totalfat += mealDataR.fat

        }
    }

