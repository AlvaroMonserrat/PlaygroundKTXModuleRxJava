package com.rrat.playgroundktxmodulerxjava.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _livaData: MutableLiveData<Int> = MutableLiveData(0)
    val liveData: LiveData<Int> = _livaData

    var resultLiveData: LiveData<String>? = MutableLiveData()

    fun getOneShotLiveData()
    {
        resultLiveData = liveData {
            val data = taskInBackground()
            emit(data)
        }
    }

    private suspend fun taskInBackground() : String
    {
        delay(2000)
        return "LiveData one-shot"
    }


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