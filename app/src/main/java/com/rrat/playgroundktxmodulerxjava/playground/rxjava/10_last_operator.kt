package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable



fun main()
{
    println("main starts")
    val disposable = lastOperator()
        //.last(User(0, "default", 10)) //Return Single
        //.lastElement()
        .lastOrError()
        .subscribe(
        {
            println("onNext: $it")
        },
        {
            println("onError: $it")
        },
    )
    println("main finish")
    //Thread.sleep(1000)
    disposable.dispose()
}

fun lastOperator(): Observable<User>
{
    return Observable.fromIterable(emptyList())
}
