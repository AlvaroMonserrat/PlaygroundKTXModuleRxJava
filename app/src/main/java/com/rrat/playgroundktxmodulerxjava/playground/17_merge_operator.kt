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

}

fun getUser(): Observable<User> {
    return Observable.fromIterable(mUserList)
}
fun getUserProfile(): Observable<UserProfile> {
    return Observable.fromIterable(mUserListProfile)
}

/*
fun mergeOperator(): Observable<Any>{
    return Observable.merge()
}*/
