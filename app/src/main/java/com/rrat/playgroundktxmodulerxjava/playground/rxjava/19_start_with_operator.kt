package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

fun main()
{
    println("main starts")
    val disposable = startWithOperator()
        .subscribe(
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
    println("main finish")
    //Thread.sleep(3000)
    disposable.dispose()
}


fun startWithOperator(): Observable<Int> {
    return getNum1To100().startWith(Single.just(0))
}
