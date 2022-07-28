package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable

val mUserListProfile = mutableListOf<UserProfile>(
    UserProfile(1, "demo1", 15, ""),
    UserProfile(2, "demo2", 18, ""),
    UserProfile(3, "demo3", 20, ""),
    UserProfile(4, "demo4", 21, ""),
    UserProfile(5, "demo5", 23, ""),
    UserProfile(6, "demo6", 22, ""),
)


fun main()
{
    println("main starts")
    val disposable = mergeOperator()
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

fun getUser(): Observable<User> {
    return Observable.fromIterable(mUserList)
}
fun getUserProfile(): Observable<UserProfile> {
    return Observable.fromIterable(mUserListProfile)
}

fun mergeOperator(): Observable<Any>{
    return Observable.merge(getUser(), getUserProfile())
}
