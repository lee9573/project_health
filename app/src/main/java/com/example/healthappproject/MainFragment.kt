package com.example.healthappproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.replace
import com.example.healthappproject.databinding.FragmentBlankBinding
//*
// main Fragment
// 애플리케이션의 여러 fragment들의 정보를 표시하는 페이지
// *//
class MainFragment : Fragment() {

    var binding : FragmentBlankBinding? = null

    //TODO : 이 함수를 viewmodel 로 옮기기.
    fun replaceFragment(viewID : Int , frg : Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().run {
            replace(viewID , frg)
            commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰가 확실하게 생성되고 나서, 안정성 up

        binding?.run {
            replaceFragment(showfirstfrg.id , ShowFirstGoalFragment.newInstance())
            //TODO 앞으로 받올 프래그먼트를 여기에다 추가
        }

    }

    }

