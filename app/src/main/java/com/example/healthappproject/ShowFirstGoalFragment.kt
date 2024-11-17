package com.example.healthappproject
/**
 * ShowFirstGoalFragment.kt
 * + MainActivity
 * + GoalFragment
 * 작성자 : 우상천
 * 목적 : GoalFragment의 첫번째 리스트 내용을 표시하는 프래그먼트를 구현
 *       애플리케이션 메인의 최상단, Goal페이지 최상단에서 최우선 목표를 보여주는 기능
 * 사용 방식 : 사용할 위치에 존재하는 프레임 레이아웃에 replace를 해서 사용한다
 * 사용 파일 : MainActivity , GoalFragment
 * **/
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthappproject.databinding.FragmentShowFirstGoalBinding

class ShowFirstGoalFragment : Fragment() {

    var binding : FragmentShowFirstGoalBinding? = null
    var viewModel : RecyclerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowFirstGoalBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(RecyclerViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let{
            //it.firstContents.text = viewModel?.getFirstItem()

        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ShowFirstGoalFragment().apply {

            }
    }
}