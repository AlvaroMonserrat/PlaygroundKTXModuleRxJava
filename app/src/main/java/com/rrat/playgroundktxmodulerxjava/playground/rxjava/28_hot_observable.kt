package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.observables.ConnectableObservable


fun main()
{

    val hotObservable = hotObservable()

    /*It is not required to have an observer*/
    hotObservable.subscribeBy(
        onNext = { println("OnNext: $it")},
        onComplete = { println("onComplete") },
        onError = { println("onError") }
    )

    hotObservable.subscribeBy(
        onNext = { println("OnNex2t: $it")},
        onComplete = { println("onComplete2") },
        onError = { println("onError2") }
    )

    /*This line executes the observable*/
    hotObservable.connect()
}

fun hotObservable() : ConnectableObservable<User>{
    return Observable.fromIterable(mUserList).publish()
}