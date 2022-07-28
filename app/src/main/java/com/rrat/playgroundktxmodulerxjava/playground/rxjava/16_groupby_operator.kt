package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable

val mUserListGroupBy = mutableListOf<User>(
    User(1, "demo1", 15),
    User(2, "demo2", 18),
    User(3, "demo3", 15),
    User(4, "demo4", 21),
    User(5, "demo5", 23),
    User(6, "demo6", 23),
    User(7, "demo7", 23),
    User(8, "demo8", 21),
)

fun main()
{
    println("main starts")
    val disposable = groupByOperator()
        .groupBy { it.age }
        //.filter{ (it.key == 23) or (it.key == 21)}
        .flatMapSingle { group->group.toList() }
        .subscribe(
            {
                //println("onNext: $it")
              /*  group-> group.subscribe(
                {
                    println("Key: ${group.key} - value $it")
                },
                {
                    println("onError: $it")
                }
                 )*/
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
    //Thread.sleep(3000)
    disposable.dispose()
}

fun groupByOperator(): Observable<User>
{
    return Observable.fromIterable(mUserListGroupBy)
}
