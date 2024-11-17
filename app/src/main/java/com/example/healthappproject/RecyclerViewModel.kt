package com.example.healthappproject

import android.text.Editable
import android.text.TextUtils.replace
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.RecyclerView

//Goal Fragment의 데이터 클래스
data class goalViewItem(
    var title : String,
    var contents : String,
    var checked : Boolean)
//일반 클래스와 다름없다. 단지 라이브 데이터만 있을 뿐이다.
class RecyclerViewModel : BaseViewModel() {
    private val _itemList = MutableLiveData<MutableList<goalViewItem>>(mutableListOf())
    val itemList: LiveData<MutableList<goalViewItem>> get() = _itemList

    fun addItem() {
        // 기본 아이템 생성
        val newItem = goalViewItem( title = "title",
                                    contents = "contents",
                                    checked = false )

        // 현재 리스트에 추가
        if (_itemList.value == null) {
            // _itemList.value가 null일 경우 새로운 리스트를 생성하고 추가
            _itemList.value = mutableListOf(newItem)

        } else {
            // 기존 리스트에 아이템 추가
            _itemList.value?.let {
                it.add(newItem)
                _itemList.value = it // LiveData 변경 알림
            }
        }
    }

    fun dltItem() {
        _itemList.value?.let { currentList -> //currentList = _itemList.value
            val itemsToRemove = currentList.filter { it.checked } //체크리스트 체크된 항목만 필터링하여 리스트에 저장
            currentList.removeAll(itemsToRemove) //필터링 된 리스트들을 제거
            _itemList.value = currentList // 업데이트된 리스트를 LiveData에 업데이트
        }
    }

    fun getListData(mode : String , index : Int) : String {
        //해당 리스트의 데이터를 반환하는 함수
        val currentList = _itemList.value ?: return ""
        if (mode == "contents") {
            return currentList[index].contents
        }
        else if (mode == "title") {
            return currentList[index].title
        }
        else return ""
    }

    fun updateListData(title : String , contents : String , index: Int) {
        //해당 리스트의 데이터를 업데이트 하는 함수
        val currentList = _itemList.value ?: return
        currentList[index].title = title
        currentList[index].contents = contents
        _itemList.value = currentList
    }

    // 1번 리스트의 내용을 반환하는 메서드
    fun getFirstItem() : String {
        val currentList = _itemList.value ?: return ""
        return "${currentList[0].title}\n${currentList[0].contents}"
    }

    val itemTouchHelper by lazy {
        val simpleItemlTouchCallback = object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                val adapter = recyclerView.adapter as RecyclerViewAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition

                adapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)

                if(actionState == ACTION_STATE_DRAG){
                    viewHolder?.itemView?.alpha = 0.5f
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)

                viewHolder?.itemView?.alpha = 1f
            }

        }
        ItemTouchHelper(simpleItemlTouchCallback)

    }



}