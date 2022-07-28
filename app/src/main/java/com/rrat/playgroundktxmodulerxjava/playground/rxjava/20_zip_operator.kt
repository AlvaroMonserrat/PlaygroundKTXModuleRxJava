package com.rrat.playgroundktxmodulerxjava.playground

import io.reactivex.rxjava3.core.Observable


data class BlogDetails(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String,
    val user: User
    )

data class Blog(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String
)

val mBlogList = mutableListOf<Blog>(
    Blog(1, 1, "title1", "content1"),
    Blog(2, 1, "title2", "content2"),
    Blog(3, 2, "title1", "content1"),
    Blog(4, 2, "title2", "content2"),
    Blog(5, 2, "title3", "content3"),
    Blog(6, 1, "title1", "content1"),
    Blog(7, 13, "title1", "content1"),
)

fun main()
{
    println("main starts")
    val disposable = zipTwoList()
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
    //Thread.sleep(3000)
    disposable.dispose()
}

/*
Combine the emission of multiples Observables together via a specified function and
* emits for each combination based on the results of this function
* */
fun zipOperator(): Observable<Any> {
    val num = Observable.just(1, 2, 3, 4 ,5)
    val letter = Observable.just("A", "B", "C", "D")

    return Observable.zip(num, letter) { t1, t2 -> "$t1$t2" }
}

fun getUserList(): Observable<User>{
    return Observable.fromIterable(mUserList)
}

fun getBlogList(): Observable<Blog>{
    return Observable.fromIterable(mBlogList)
}

fun zipTwoList(): Observable<Any>{
    return Observable.zip(getUserList(), getBlogList()) {
            user, blog -> getBlogDetail(user, blog)
    }
}

fun getBlogDetail(user: User, blog: Blog) : BlogDetails
{
    //if (blog.userId == user.id)
    return BlogDetails(user.id, blog.userId, blog.title, blog.content, user)
    //return BlogDetails(0, 0, "", "", User(0, "", 0))
}

