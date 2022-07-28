package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


fun main()
{
    println("main starts")
    //publishSubject()
    publishSubjectTwo()
    //Thread.sleep(8000)
    println("main finishes")
}

fun publishSubject()
{
    val observable = Observable
        .interval(1, TimeUnit.SECONDS)
        .takeWhile { it <= 5 }
        .subscribeOn(Schedulers.io())


    val subject = PublishSubject.create<Long>()

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

fun publishSubjectTwo()
{

    val subject = PublishSubject.create<Int>()

    subject.subscribeBy(
        onNext = { println("OnNext1: $it")},
        onComplete = { println("onComplete1")},
        onError = { println("onError1")}
    )

    subject.onNext(123)
    subject.onNext(1234)
    subject.onComplete()

    subject.subscribeBy(
        onNext = { println("OnNext2: $it")},
        onComplete = { println("onComplete2")},
        onError = { println("onError2")}
    )
}