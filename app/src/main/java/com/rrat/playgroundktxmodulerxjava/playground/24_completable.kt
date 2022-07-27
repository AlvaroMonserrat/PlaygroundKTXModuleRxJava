package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.RuntimeException

fun createCompletable(): Completable {
    return Completable.create { emitter ->
        try {
            if(!emitter.isDisposed)
            {
                saveData(1)
                emitter.onComplete()
            }
        }catch (e: Exception){
            emitter.onError(e)
        }
        emitter.onComplete()
    }
}

fun completableObserver(): CompletableObserver {
    return object : CompletableObserver
    {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onError(e: Throwable) {
            println("onError")
        }

        override fun onComplete() {
            println("onComplete")
        }
    }
}

fun saveData(id: Int?)
{
    Thread.sleep(1000)
    if (id != null)
        println("data was saved successfully")
    else
        throw RuntimeException()
}


fun main()
{
    val observable = createCompletable()
    observable.subscribe(completableObserver())
}
