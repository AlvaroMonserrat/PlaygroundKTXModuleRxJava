package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable



class ObserverArrayInteger: Observer<Array<Int>> {
    override fun onSubscribe(d: Disposable) {
        println("onSubscribe")
    }

    override fun onNext(value: Array<Int>) {
        println("onNext: ${value.contentToString()}")
    }

    override fun onError(e: Throwable) {
        println("onError $e")
    }

    override fun onComplete() {
        println("onComplete")
    }

}

class ObserverIterableInteger: Observer<Int> {
    override fun onSubscribe(d: Disposable) {
        println("onSubscribe")
    }

    override fun onNext(value: Int) {
        println("onNext: $value")
    }

    override fun onError(e: Throwable) {
        println("onError $e")
    }

    override fun onComplete() {
        println("onComplete")
    }

}

fun main()
{
    val mList = mutableListOf(1, 2, 3, 4, 5, 6, 7 ,8, 9 ,10, 11 ,12)
    val arrayNum1 = arrayOf(1, 2, 3, 4, 5, 6, 7 ,8, 9 ,10, 11 ,12)
    val arrayNum2 = arrayOf(10, 20, 30, 40, 50, 60, 70 ,80, 90 ,100, 110 ,120)

    val observableFrom = Observable.fromArray(arrayNum1, arrayNum2)
    val observableJust = Observable.just(arrayNum1, arrayNum2)
    val observableFromIterable = Observable.fromIterable(mList)

    observableFrom.subscribe(ObserverArrayInteger())
    observableJust.subscribe(ObserverArrayInteger())
    observableFromIterable.subscribe(ObserverIterableInteger())
}
