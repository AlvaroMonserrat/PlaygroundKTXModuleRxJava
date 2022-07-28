package com.rrat.playgroundktxmodulerxjava

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _backgroundLiveData = MutableLiveData<Int>()
    val backgroundLiveData: LiveData<Int> = _backgroundLiveData

    private val _backgroundStateFlow = MutableStateFlow<Int>(0)
    val backgroundStateFlow: StateFlow<Int> = _backgroundStateFlow.asStateFlow()

    fun getDataFromRxJava(){
        compositeDisposable.add(createObservableRange()
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _backgroundLiveData.postValue(it)

                    Log.i("TEST", "Value $it")
                },
                {
                    Log.i("TEST", "ERROR ${it.message}")
                },
                {
                    Log.i("TEST", "Completed")
                }

            ))
    }

    fun getDataFromCoroutine()
    {
        viewModelScope.launch(Dispatchers.Default) {
            repeat(1000){
                delay(1)
                _backgroundStateFlow.value = it
            }
        }
    }

    private fun createObservableRange(): Observable<Int>
    {
        return Observable.range(0, 10000)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        Log.i("TEST", "COMPOSITE DISPOSABLE CLEAR")
        super.onCleared()
    }
}