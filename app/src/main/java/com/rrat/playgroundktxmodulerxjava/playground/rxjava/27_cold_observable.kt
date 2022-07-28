package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy


fun main()
{
    coldObservable().subscribeBy(
        onNext = { println("OnNext: $it") },
        onComplete = { println("onComplete") },
        onError = { println("onError") },
    )

    coldObservable().subscribeBy(
        onNext = { println("OnNext: $it") },
        onComplete = { println("onComplete") },
        onError = { println("onError") },
    )

    coldObservable().subscribeBy(
        onNext = { println("OnNext: $it") },
        onComplete = { println("onComplete") },
        onError = { println("onError") },
    )
    Thread.sleep(1000)
}

fun coldObservable() : Observable<User>
{
    return Observable.fromIterable(mUserList)
}