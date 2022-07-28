package com.rrat.playgroundktxmodulerxjava

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _livaData: MutableLiveData<Int> = MutableLiveData(0)
    val liveData: LiveData<Int> = _livaData

    fun setLiveData(newValue: Int)
    {
        _livaData.value = newValue + _livaData.value!!
    }

    fun getSpecialNumber()
    {
        _livaData.value = 0
        viewModelScope.launch {
            delay(5000)
            _livaData.postValue(1021)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TEST","ON CLEARED VIEW MODEL")

    }
}