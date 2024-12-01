package com.example.healthappproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.RecyclerView
import com.example.healthappproject.RecyclerViewAdapter
import com.example.healthappproject.viewmodels.BaseViewModel
<<<<<<< HEAD
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
=======
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec

//Goal Fragment의 데이터 클래스
data class goalViewItem(
    var title : String,
    var contents : String,
    var checked : Boolean)
//일반 클래스와 다름없다. 단지 라이브 데이터만 있을 뿐이다.
class RecyclerViewModel : BaseViewModel() {
<<<<<<< HEAD
    val firebaseUrl = "https://healthappfirebase-ed61c-default-rtdb.firebaseio.com/"
    private val database: DatabaseReference = FirebaseDatabase.getInstance(firebaseUrl).getReference("sangcheon")
    //FirebaseDatabase.getInstacne() : 구글 서비스 제이슨에 있는 파이어베이스 주소를 가져온다.
    //getReferece("path") : 해당 파이어베이스 주소 안에 있는 path, 디렉토리를 가져온다.
    //TODO 이 코드를 구글 서비스 제이슨에 올리고, 디렉토리를 이름별로 파서, 팀원들 참조하게 하기 .
    private var isUpdatingFromFirebase = false

    private val _itemList = MutableLiveData<MutableList<goalViewItem>>(mutableListOf())
    val itemList: LiveData<MutableList<goalViewItem>> get() = _itemList

    init {
        // 1. Firebase 데이터 가져오기
        fetchItemsFromFirebase()

        // 2. LiveData가 변경될 때 Firebase에 자동 업데이트
        _itemList.observeForever { updatedList ->
            saveToFirebase(updatedList)
        }
    }

    // Firebase 데이터 읽기
    private fun fetchItemsFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isUpdatingFromFirebase = true
                val items = mutableListOf<goalViewItem>()
                snapshot.children.forEach { child ->
                    val item = child.getValue(goalViewItem::class.java)
                    if (item != null) {
                        items.add(item)
                    }
                }
                _itemList.value = items
                isUpdatingFromFirebase = false
            }

            override fun onCancelled(error: DatabaseError) {
                // 에러 처리
            }
        })
    }
    // Firebase에 데이터 저장

    private fun saveToFirebase(updatedList: List<goalViewItem>?) {
        if (!isUpdatingFromFirebase) {
            database.setValue(updatedList)
        }
    }

=======
    private val _itemList = MutableLiveData<MutableList<goalViewItem>>(mutableListOf())
    val itemList: LiveData<MutableList<goalViewItem>> get() = _itemList

>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
    fun addItem(title: String , text: String , checked: Boolean = false) {
        // 기본 아이템 생성
        val newItem = goalViewItem(title , text , checked)

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

<<<<<<< HEAD
    // 1번째 아이템의 title을 반환하는 메서드
    fun getTitle(): String {
        val currentList = _itemList.value ?: return "No data"
        return if (currentList.isNotEmpty()) {
            "${currentList[0].title}"
=======
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
    fun getFirstItem(): String {
        val currentList = _itemList.value ?: return "No data"
        return if (currentList.isNotEmpty()) {
            "${currentList[0].title}\n${currentList[0].contents}"
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
        } else {
            "No data"
        }
    }
<<<<<<< HEAD
    //1번째 아이템의 contents를 반환하는 메서드
    fun getContents(): String {
        val currentList = _itemList.value ?: return " "
        return if (currentList.isNotEmpty()) {
            "${currentList[0].contents}"
        } else {
            " "
        }
    }

    fun swapItems(fromPosition: Int, toPosition: Int) { // 삽입 로직 사용
        //처음엔 collection.swap 사용했으나, 애니메이션이 사용자 친화적이 아니여서 로직을 바꿨다.
        val currentItems = _itemList.value ?: return

        val itemToMove = currentItems[fromPosition] //바꾸고자 하는 아이템 추출
        currentItems.removeAt(fromPosition) //아이템 제거
        currentItems.add(toPosition, itemToMove) //바꾸고자 하는 위치로 삽입

        _itemList.value = currentItems
    }


    private var dragFrom = -1
    private var dragTo = -1
    //추가한 이유 : 드래그앤 드롭 애니메이션을 지키기 위해서. 즉, clearview에서 사용하기 위해서
    val itemTouchHelper by lazy {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, 0
        ) {
=======


    val itemTouchHelper by lazy {
        val simpleItemlTouchCallback = object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {
>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
<<<<<<< HEAD
                val adapter = recyclerView.adapter as RecyclerViewAdapter

                // 드래그 시작 위치 저장
                if (dragFrom == -1) {
                    dragFrom = viewHolder.adapterPosition
                }

                // 드래그 종료 위치 갱신
                dragTo = target.adapterPosition

                // UI에서 아이템 이동
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)

                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.alpha = 0.5f // 드래그 중 시각적 효과
                }
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)

                // 드래그 중 시각적 효과 초기화
                viewHolder.itemView.alpha = 1f

                // 드래그 종료 후 위치가 유효할 경우에만 데이터 갱신
                if (dragFrom != -1 && dragTo != -1 && dragFrom != dragTo) {
                    swapItems(dragFrom, dragTo) // ViewModel의 데이터 갱신
                }

                // 드래그 위치 초기화
                dragFrom = -1
                dragTo = -1
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // 스와이프 동작 (필요 시 구현)
            }
        }

        ItemTouchHelper(simpleItemTouchCallback)
    }
=======

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



>>>>>>> 7c17066cbd6f6de34028824601435ae579813dec
}