package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable


fun main()
{
    rangeOperator().subscribe(
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

fun rangeOperator(): Observable<Int>{
    return Observable.range(1, 10)
}