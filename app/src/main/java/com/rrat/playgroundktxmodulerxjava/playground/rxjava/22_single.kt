package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

fun createSingle(): Single<List<User>> {
    return Single.create { emitter ->
        try {
            if(!emitter.isDisposed)
            {
                emitter.onSuccess(mUserList)
            }
        }catch (e: Exception){
            emitter.onError(e)
        }
    }
}

fun singleObserver(): SingleObserver<List<User>> {
    return object : SingleObserver<List<User>>
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
    }
}

fun main()
{
    val observable = createSingle()
    observable.subscribe(singleObserver())
}
