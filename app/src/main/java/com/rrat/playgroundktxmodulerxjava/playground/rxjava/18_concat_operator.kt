package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable


fun main()
{
    println("main starts")
    val disposable = concatOperator()
        .subscribe(
            {
                if (it::class.java.isAssignableFrom(UserProfile::class.java))
                {
                    println("onNext is UserProfile: $it")
                }else{
                    println("onNext: $it")
                }

            },
            {
                println("onError: $it")
            },
            {
                println("onCompleted")
            }
        )
    println("main finish")
    //Thread.sleep(3000)
    disposable.dispose()
}

fun getNum1To100(): Observable<Int>
{
    return Observable.range(1, 100)
}

fun getNum100To150(): Observable<Int>
{
    return Observable.range(101, 50)
}


fun concatOperator(): Observable<Int> {
    //return Observable.concat(getNum1To100(), getNum100To150())
    return getNum1To100().concatWith(getNum100To150())
}
