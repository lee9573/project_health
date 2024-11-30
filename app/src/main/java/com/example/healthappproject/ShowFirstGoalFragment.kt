package com.example.healthappproject
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthappproject.databinding.FragmentShowFirstGoalBinding
import com.example.healthappproject.viewmodels.RecyclerViewModel

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
        viewModel = ViewModelProvider(requireActivity()).get(RecyclerViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            viewModel?.itemList?.observe(viewLifecycleOwner) { items ->
        // 첫 번째 아이템이 있는 경우에만 업데이트
                if (items.isNotEmpty()) {
                    firstContents.text = viewModel?.getFirstItem()
                } else {
                    firstContents.text = viewModel?.getFirstItem()
                }
    }
}

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ShowFirstGoalFragment().apply {

            }
    }
}