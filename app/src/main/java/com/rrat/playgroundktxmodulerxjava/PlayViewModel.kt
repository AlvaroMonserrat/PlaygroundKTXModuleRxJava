package com.rrat.playgroundktxmodulerxjava

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val backgroundLiveData = MutableLiveData<Int>()
    val backgroundStateFlow: StateFlow<Int> = MutableStateFlow(0)

    fun getDataFromRxJava(){
        compositeDisposable.add(createObservableRange()
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    backgroundLiveData.postValue(it)

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