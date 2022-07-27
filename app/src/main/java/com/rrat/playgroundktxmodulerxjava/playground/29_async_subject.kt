package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.AsyncSubject
import java.util.concurrent.TimeUnit


fun main()
{

    println("main starts")
    asyncSubjectOnNext()
    //asyncSubject()
    //Thread.sleep(8000)
    println("main finishes")
}

fun asyncSubject()
{
    val observable = Observable.interval(1, TimeUnit.SECONDS).takeWhile { it <= 5 }

    val subject = AsyncSubject.create<Long>()

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

fun asyncSubjectOnNext()
{
    /*subject only emits the last item*/
    val subject = AsyncSubject.create<Int>()

    subject.onNext(200)
    subject.onNext(500)
    subject.onNext(700)

    /*Only emits the last item*/
    subject.subscribeBy(
        onNext = { println("OnNext subject: $it")},
        onComplete = { println("onComplete subject")},
        onError = { println("onError subject")}
    )

    /*It is necessary calling onComplete*/
    subject.onComplete()
}