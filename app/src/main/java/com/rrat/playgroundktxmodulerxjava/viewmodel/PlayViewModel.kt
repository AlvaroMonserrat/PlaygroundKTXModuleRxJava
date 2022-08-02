package com.rrat.playgroundktxmodulerxjava.viewmodel

import android.util.Log
import androidx.lifecycle.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PlayViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    /*Live Data: is an lifecycle aware obervable data holder*/
    private val _backgroundLiveData = MutableLiveData<Int>()
    val backgroundLiveData: LiveData<Int> = _backgroundLiveData

    /*State Flow: (hot stream) does similar things like LiveData but it is made using flow */
    private val _backgroundStateFlow = MutableStateFlow(0)
    val backgroundStateFlow: StateFlow<Int> = _backgroundStateFlow.asStateFlow()

    /*Shared Flow : this flow can be shared by multiple consumers*/
    private val _sharedFlow = MutableSharedFlow<Int>()
    val backgroundSharedFlow = _sharedFlow.asSharedFlow()

    /*Channel*/
    private val _channel = Channel<Int>()
    val flowChannel = _channel.receiveAsFlow()

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

    fun getDataFromCoroutineStateFlow()
    {
        viewModelScope.launch(Dispatchers.Default) {
            repeat(1000){
                delay(1)
                _backgroundStateFlow.value = it
            }
        }
    }

    fun getDataFromCoroutineSharedFlow()
    {
        viewModelScope.launch(Dispatchers.Default) {
            repeat(1000){
                delay(1)
                _sharedFlow.emit(it)
            }
        }
    }

    fun getDataFromCoroutineChannel()
    {
        val job = viewModelScope.launch{
            repeat(1000){
                delay(1)
                _channel.send(it)
            }
        }

        job.invokeOnCompletion {
            Log.i("TEST", "invokeOnCompletion")
            if (it != null) {
                Log.i("TEST", "invokeOnCompletion ${it.message}")
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