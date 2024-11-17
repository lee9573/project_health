package com.example.healthappproject

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Checkable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthappproject.databinding.FragmentGoalBinding

class GoalFragment : Fragment() {

    var binding : FragmentGoalBinding? = null
    var recyclerView : RecyclerView? = null
    var recyclerViewModel : RecyclerViewModel? = null
    var adapter : RecyclerViewAdapter? = null
    var sharedViewModel : SharedViewModel? = null

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
        binding = FragmentGoalBinding.inflate(inflater, container, false) //이 프래그먼트가 생성되면 바인딩을 만든다.
        recyclerView = binding?.recyclerView // 이 프래그먼트가 생성되면 리사이클러 뷰 객체를 만든다.
        recyclerViewModel = ViewModelProvider(this).get(RecyclerViewModel::class.java) // 이 프래그먼트가 만들어지면, 뷰모델 객체를 들고온다
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {

            replaceFragment(showfirstfrag.id , ShowFirstGoalFragment.newInstance())

            addListButton.setOnClickListener() { //ADD 버튼 액션
                recyclerViewModel?.addItem()
            }
            dltListButton.setOnClickListener() { //DELETE 버튼 액션
                recyclerViewModel?.dltItem()
            }

            recyclerViewModel?.itemList?.observe(viewLifecycleOwner) { items -> //라이브 데이터가 갱신될 때 마다 실행

                adapter = RecyclerViewAdapter(items) { itemPosition ->
                    //TODO 여기 있는 것을 제데로 이해해야한다.
                    Log.i(TAG, "this index is $itemPosition")
                    sharedViewModel?.updateNumber(itemPosition) //공유데이터 뷰모델에 포지션을 넘겨줌
                    findNavController().navigate(R.id.action_goalFragment_to_showItemPageFragment)
                }

                recyclerView.adapter = adapter //<- 계속해서 어댑터를 새로 할당하는것은 위험하다.이전 어댑터의 상태를 읽게 된다.
                //adapter?.updateItems(items)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            } //옵저버에서 지속적으로 라이브 데이터를 준다.

            recyclerViewModel?.itemTouchHelper?.attachToRecyclerView(recyclerView) //드래그 앤 드롭 기능

        }
    }


}
