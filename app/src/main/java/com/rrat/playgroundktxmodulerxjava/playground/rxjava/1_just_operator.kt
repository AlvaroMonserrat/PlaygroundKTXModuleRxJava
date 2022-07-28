package com.rrat.playgroundktxmodulerxjava.playground.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.concurrent.thread


class ObserverInteger: Observer<List<Int>>{
    override fun onSubscribe(d: Disposable) {
        println("onSubscribe")
    }

    override fun onNext(value: List<Int>) {
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
    //MAX ITEMS WHICH JUST CAN RECEIVE IS 10.
    //val observable = Observable.just(100, 200, 300, 400, 500)

    println("Init Main")
    val observable: Observable<List<Int>>? = routine()
    val observer = ObserverInteger()
    observable?.subscribe(observer)
    println("Finish Main")
}

fun routine() : Observable<List<Int>>?
{
    val mList = mutableListOf<Int>()
    thread {
        println("Init Thread, waiting...")
        for (i in 0..10)
        {
            mList.add(i)
            Thread.sleep(200)
        }
        println("Finish Thread")
    }.join()
    return Observable.just(mList)
}
