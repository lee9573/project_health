package com.example.healthappproject

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.healthappproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //메인액티비티는 네비게이션 구현으로 끝?
        val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        val navController = binding.fragmentNav.getFragment<NavHostFragment>().navController
        //네비게이션 프래그먼트 객체
        binding.bottonNav.setupWithNavController(navController)
        //바텀 네비게이션 객체에 연결한 것.

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}