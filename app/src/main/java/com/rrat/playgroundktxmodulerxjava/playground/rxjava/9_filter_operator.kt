package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable


data class User(val id: Long, val name: String, val age: Int)
data class UserProfile(val id: Long, val name: String, val age: Int, val img: String)

val mUserList = mutableListOf<User>(
    User(1, "demo1", 15),
    User(2, "demo2", 18),
    User(3, "demo3", 20),
    User(4, "demo4", 21),
    User(5, "demo5", 23),
    User(6, "demo6", 22),
)



fun main()
{
    println("main starts")
    val disposable = filterOperator().filter{
        //it.age < 20
        it.name == "demo5"
    }.subscribe(
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

fun filterOperator(): Observable<User>
{
    return Observable.fromIterable(mUserList)
}
