package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable

fun createMaybe(): Maybe<List<User>> {
    return Maybe.create { emitter ->
        try {
            if(!emitter.isDisposed)
            {
                emitter.onSuccess(mUserList)
            }
        }catch (e: Exception){
            emitter.onError(e)
        }
        emitter.onComplete()
    }
}

fun maybeObserver(): MaybeObserver<List<User>> {
    return object : MaybeObserver<List<User>>
    {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }
        override fun onSuccess(t: List<User>) {

            t.forEach{
                println("onSuccess $it")
            }
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
    val observable = createMaybe()
    observable.subscribe(maybeObserver())
}
