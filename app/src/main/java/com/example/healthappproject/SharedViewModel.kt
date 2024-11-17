package com.example.healthappproject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    //private val _textData = MutableLiveData<String>()
    //val textData: LiveData<String> get() = _textData

    private val _numberData = MutableLiveData<Int>()
    val numberData: LiveData<Int> get() = _numberData

    //fun updateText(data: String) {
    //    _textData.value = data
    //  }

    fun updateNumber(data: Int) {
        _numberData.value = data
    }
}