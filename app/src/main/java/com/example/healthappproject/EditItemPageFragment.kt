package com.example.healthappproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.Toast
=======
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthappproject.databinding.FragmentEditItemPageBinding
import com.example.healthappproject.databinding.FragmentGoalBinding
import com.example.healthappproject.viewmodels.RecyclerViewModel


class EditItemPageFragment : DialogFragment() {

    var binding : FragmentEditItemPageBinding? = null
    var recyclerViewModel : RecyclerViewModel? = null

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
        binding = FragmentEditItemPageBinding.inflate(inflater, container, false) //이 프래그먼트가 생성되면 바인딩을 만든다.

        recyclerViewModel = ViewModelProvider(requireActivity()).get(RecyclerViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //1. 바인딩 따오기
        //2. 세이브 버튼 액션 만들기
        //3. 데이터를 어떻게 넘겨주지?
        //2-1. 데이터 만든거 세이브 버튼 누르면 addItem 호출되서 아이템 생기게 하면 된다.
        binding?.run {
            saveButton.setOnClickListener() {
                val title = editTitle.text.toString()
                val text = editText.text.toString()
<<<<<<< HEAD
                if(title.isNotEmpty() && text.isNotEmpty()) {
                    recyclerViewModel?.addItem(title, text)
                    dismiss()
                }
                else {
                    Toast.makeText(requireContext(), "Input text!", Toast.LENGTH_SHORT).show()
                }
=======

                recyclerViewModel?.addItem(title, text)
                dismiss()
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
            }
        }



    }

    companion object {

        @JvmStatic
        fun newInstance() =
            EditItemPageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}