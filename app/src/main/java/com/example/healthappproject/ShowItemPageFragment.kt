package com.example.healthappproject

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.healthappproject.databinding.FragmentShowFirstGoalBinding
import com.example.healthappproject.databinding.FragmentShowItemPageBinding

class ShowItemPageFragment : Fragment() {

    var binding : FragmentShowItemPageBinding? = null
    var recyclerViewModel : RecyclerViewModel? = null
    var sharedViewModel : SharedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowItemPageBinding.inflate(inflater, container, false)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        recyclerViewModel = ViewModelProvider(this).get(RecyclerViewModel::class.java)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            Log.i(TAG,"item page run")
            sharedViewModel?.numberData?.observe(viewLifecycleOwner) { items ->

                // 이제 리사이클러 뷰에서 선택 될 때마다 이 넘버데이터는 업데이트 된다.
                titlePlainText.setText(recyclerViewModel?.getListData("title", items))
                contentsPlainText.setText(recyclerViewModel?.getListData("contents", items))
                Log.i(TAG, "this index is $items at item pages")
                saveButton.setOnClickListener() {
                    recyclerViewModel?.updateListData(titlePlainText.getText().toString() ,
                        contentsPlainText.getText().toString()
                        , items)
                    Log.i(TAG, "this index is $items at item pages")
                }

            }

        }

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ShowItemPageFragment().apply {
            }
    }
}