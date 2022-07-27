package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


fun testSchedulerObservable(): Observable<User>{
    return Observable.create { emitter ->
        try {
            if(!emitter.isDisposed)
            {
                println("Observable: $emitter ThreadName: ${Thread.currentThread().name}")
                mUserList.forEach{
                    emitter.onNext(it)
                }
            }
        }catch (e: Exception){
            emitter.onError(e)
        }
    }
}

fun testSchedulerObserver(): Observer<User> {
    return object : Observer<User>
    {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onError(e: Throwable) {
            println("onError")
        }

        override fun onComplete() {
            println("onComplete")
        }

        override fun onNext(t: User) {
            println("onNext: $t ThreadName: ${Thread.currentThread().name}")
        }
    }
}



fun main()
{
    val observable = testSchedulerObservable()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe(testSchedulerObserver())

    Thread.sleep(2000)
}
