package com.example.healthappproject.viewmodels

import androidx.lifecycle.LiveData //변경 가능한 데이터를 관찰 가능한 형태로 제공하는 Android Architecture Component
import androidx.lifecycle.MutableLiveData // 내부에서 변경 가능한 라이브데이타
import androidx.lifecycle.ViewModel

class ChallengeViewModel : ViewModel() {

    val videoUrls = listOf(
        "https://www.youtube.com/watch?v=qkQdIMW1xlw",
        "https://www.youtube.com/watch?v=c_5ENJWekbQ",
        "https://www.youtube.com/watch?v=KnXCJw_zw8Y",
        "https://www.youtube.com/watch?v=QSZ0mUGO_CA"
    )

    val videoIds = listOf(
        "qkQdIMW1xlw",
        "c_5ENJWekbQ",
        "KnXCJw_zw8Y",
        "QSZ0mUGO_CA"
    )

    companion object {
        const val TOTAL_CHALLENGES = 9  // 총 챌린지 수를 상수로 정의
    }

    private val _challenges: MutableLiveData<List<String>> = MutableLiveData()
    val challenges: LiveData<List<String>> get() = _challenges

    private val _completedChallenges: MutableLiveData<Int> = MutableLiveData(0)
    val completedChallenges: LiveData<Int> get() = _completedChallenges

    init {
        loadChallenges()
    }

    private fun loadChallenges() {
        // 하드코딩된 챌린지 데이터
        _challenges.value = listOf(
            "Push-up Challenge",
            "Running Challenge",
            "Plank Challenge",
            "Squat Challenge",
            "Jumping Jacks",
            "Yoga Session",
            "Meditation Practice",
            "Cycling",
            "Walking Goal"
        )
    }

    fun updateCompletedChallenges(isChecked: Boolean) {
        _completedChallenges.value = if (isChecked) {
            (_completedChallenges.value ?: 0) + 1
        } else {
            (_completedChallenges.value ?: 0) - 1
        }
    }

    fun resetChallenges() {
        _completedChallenges.value = 0
        loadChallenges()
    }
}
