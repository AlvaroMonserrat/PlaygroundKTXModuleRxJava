package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun createFlowable(): Flowable<Int> {
    return Flowable.range(1, 100)
}


fun createNewObservable(): Observable<Int> {
    return Observable.range(1, 100)
}

fun flowableObserver(): Observer<Int> {
    return object : Observer<Int>
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

        override fun onNext(t: Int) {
            println("onNext: $t")
        }
    }
}



fun main()
{
    val observable = createNewObservable()
        //.onBackpressureBuffer()
        .toFlowable(BackpressureStrategy.DROP)
        .observeOn(Schedulers.io(), false)
    observable.subscribe(
        {
            println("onNext: $it")
        },
        {
            println("onError ${it.message}")
        },
        {
            println("onComplete")
        }
    )
    Thread.sleep(5000)
}
