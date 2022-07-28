package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun main()
{
    val disposable = intervalOperator().subscribe(
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

    Thread.sleep(20000)
    disposable.dispose()
    Thread.sleep(1000)
}

fun intervalOperator(): Observable<Long> {
    return Observable.interval(1, 1, TimeUnit.SECONDS).takeWhile{
        value -> value <= 10
    }
}