package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


fun main()
{
    println("main starts")
    behaviorSubjectTwo()
    Thread.sleep(8000)
    println("main finishes")
}

fun behaviorSubjectOne()
{
    val observable = Observable
        .interval(1, TimeUnit.SECONDS)
        .takeWhile { it <= 5 }

    val subject = BehaviorSubject.create<Long>()

    observable.subscribe(subject)

    subject.subscribeBy(
        onNext = { println("OnNext1: $it")},
        onComplete = { println("onComplete1")},
        onError = { println("onError1")}
    )
    subject.subscribeBy(
        onNext = { println("OnNext2: $it")},
        onComplete = { println("onComplete2")},
        onError = { println("onError2")}
    )
}

fun behaviorSubjectTwo()
{
    val subject = BehaviorSubject.create<Int>()

    subject.onNext(5)

    subject.subscribeBy(
        onNext = { println("OnNext 3 : $it")},
        onComplete = { println("onComplete 3")},
        onError = { println("onError 3")}
    )

    subject.onNext(10)
    subject.onNext(20)

    subject.subscribeBy(
        onNext = { println("OnNext 4 : $it")},
        onComplete = { println("onComplete 4")},
        onError = { println("onError 4")}
    )

    subject.onNext(30)
    subject.onNext(40)

    subject.onComplete()

}