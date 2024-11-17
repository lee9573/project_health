package com.example.healthappproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.END
import com.example.healthappproject.databinding.ItemRecyclerviewBinding
import androidx.recyclerview.widget.RecyclerView
//데이터 클래스, 항목 xml, 그리고 그에대한 어댑터는 한번만 설정해놓고, 나머지는 이 리사이클러 뷰를 가진 뷰에서 로직을 처리한다.
class RecyclerViewAdapter(private var list : MutableList<goalViewItem>,
                          private val onItemClick: (Int) -> Unit
                            ): RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.Holder {
        //뷰 홀더가 맨 처음 실행될 때 가장 먼저 실행되는 로직
        //뷰 홀더가 생성 될 때 실행되는 로직

        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        //다른 화면으로 나갔다 들어왓을 때,
        //리사이클러 뷰를 스크롤 할때 호출된다.
        //리사이클러 뷰에서 가장 활발하게 호출된다. -> 최신화가 가장 잘 되는 함수
        val currentItem = list[position]
        holder.bind(currentItem)

        holder.binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
           currentItem.checked = isChecked // 체크박스 상태 업데이트
        }

    }

    override fun getItemCount(): Int = list.size
    //처음 초기화 될 때,
    //아이템을 추가하거나 삭제할때,

    fun updateItems(newItems: MutableList<goalViewItem>) {
        list = newItems
        notifyDataSetChanged() // 데이터 변경 알림
    }

    inner class Holder(val binding : ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        //홀더는 리스트 한칸이다.
        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition) // 클릭된 아이템 전달
            }
    }
        fun bind(item : goalViewItem) {
            binding.textTitle.text = item.title
            binding.textContents.text = item.contents
            binding.checkBox.isChecked = item.checked
        }
}
}
