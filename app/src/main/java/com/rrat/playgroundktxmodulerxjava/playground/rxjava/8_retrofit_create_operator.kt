package com.rrat.playgroundktxmodulerxjava.playground.rxjava

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("octocat")
    fun listRepos(@Path("user") user: String?): Call<String>?

    @GET("octocat")
    fun getOctoCat(): Call<String>?
}

fun main()
{
    println("INIT")

    val disposable = createOperatorRetrofit().subscribe(
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

    Thread.sleep(3000)
    disposable.dispose()
    println("FINISH")

}


fun createOperatorRetrofit(): Observable<String> {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val service = retrofit.create(GitHubService::class.java)

    val callOctoCat = service.getOctoCat()

    return Observable.create {
        callOctoCat?.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                //println("On Response: ${response.body()}")
                response.body()?.let { it1 -> it.onNext(it1) }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("on Failure: ${t.message}")
                it.onError(t)
            }

        })
    }
}