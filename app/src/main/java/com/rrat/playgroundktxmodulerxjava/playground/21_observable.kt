package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


fun createObservable(): Observable<Int>{
    return Observable.create { emitter ->
        try {
            if(!emitter.isDisposed)
            {
                for(i in 0..100)
                {
                    emitter.onNext(i)
                }
            }
        }catch (e: Exception){
            emitter.onError(e)
        }
        emitter.onComplete()
    }
}

fun observer(): Observer<Int> {
    return object : Observer<Int>
    {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onNext(t: Int) {
            println("onNext: $t")
        }

        override fun onError(e: Throwable) {
            println("onError")
        }

        override fun onComplete() {
            println("onComplete")
        }

    }
}


fun main()
{
    val observable = createObservable()
    observable.subscribe(observer())
}
