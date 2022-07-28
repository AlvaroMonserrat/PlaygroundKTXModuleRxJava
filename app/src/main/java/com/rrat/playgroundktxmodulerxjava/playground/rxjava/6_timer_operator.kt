package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


fun main()
{
    val disposable = timerOperator().subscribe(
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

    Thread.sleep(3000)
    disposable.dispose()
}

fun timerOperator(): Observable<Long> {
    return Observable.timer(2, TimeUnit.SECONDS)
}