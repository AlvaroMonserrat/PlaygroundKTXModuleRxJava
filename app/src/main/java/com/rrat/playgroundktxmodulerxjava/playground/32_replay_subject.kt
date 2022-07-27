package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.util.concurrent.TimeUnit


fun main()
{
    println("main starts")
    replaySubjectTwo()
    Thread.sleep(8000)
    println("main finishes")
}

fun replaySubject()
{
    val observable = Observable
        .interval(1, TimeUnit.SECONDS)
        .takeWhile { it <= 5 }
        .subscribeOn(Schedulers.io())

    val subject = ReplaySubject.create<Long>()
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

    subject.subscribeBy(
        onNext = { println("OnNext3: $it")},
        onComplete = { println("onComplete3")},
        onError = { println("onError3")}
    )

}

fun replaySubjectTwo()
{
    val subject = ReplaySubject.create<Int>()
    subject.onNext(0)
    subject.onNext(1)
    subject.onNext(2)

    subject.subscribeBy(
        onNext = { println("OnNext1: $it")},
        onComplete = { println("onComplete1")},
        onError = { println("onError1")}
    )
    subject.onNext(3)
    subject.onNext(4)

    subject.subscribeBy(
        onNext = { println("OnNext2: $it")},
        onComplete = { println("onComplete2")},
        onError = { println("onError2")}
    )
}