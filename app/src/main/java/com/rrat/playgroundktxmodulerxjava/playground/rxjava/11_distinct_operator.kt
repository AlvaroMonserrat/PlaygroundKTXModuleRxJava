package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable


val mUserListWithDuplicate = mutableListOf<User>(
    User(1, "demo1", 15),
    User(2, "demo2", 18),
    User(3, "demo3", 20),
    User(4, "demo4", 21),
    User(5, "demo5", 23),
    User(1, "demo1", 15), //Duplicated
    User(7, "demo7", 22),
    User(8, "demo8", 22),
)

fun main()
{
    println("main starts")
    val disposable = distinctOperator()
        //.distinct{ it.age }
        .distinct()
        .subscribe(
            {
                println("onNext: $it")
            },
            {
                println("onError: $it")
            },
            {
                println("onCompleted")
            }
        )
    println("main finish")
    //Thread.sleep(1000)
    disposable.dispose()
}

fun distinctOperator(): Observable<User>
{
    return Observable.fromIterable(mUserListWithDuplicate)
}
