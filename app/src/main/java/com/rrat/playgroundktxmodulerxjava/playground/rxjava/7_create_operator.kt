package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable

val mList = mutableListOf(1, 2, 3, 4, 5, 6, 7 ,8, 9 ,10, 11 ,12)

fun main()
{
    val disposable = createOperator().subscribe(
        {
            println("onNext: $it")
        },
        {
            println("onError: $it")
        },
        {
            println("onCompleted")
        }
    )

    //Thread.sleep(3000)
    disposable.dispose()
}

fun createOperator(): Observable<Int> {
    return Observable.create {
        try {
            for (i in mList) {
                it.onNext(i * 5)
            }
            it.onComplete()
        } catch (e: Exception) {
            it.onError(e)
        }
    }
}