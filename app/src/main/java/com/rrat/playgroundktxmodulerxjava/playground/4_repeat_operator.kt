package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable

fun main()
{
    repeatOperator().subscribe(
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
}

fun repeatOperator(): Observable<Int> {
    return Observable.range(1, 10).repeat(2)
}