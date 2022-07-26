package com.rrat.playgroundktxmodulerxjava

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rrat.playgroundktxmodulerxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disposable = intervalOperator().subscribe(
            {
                Log.i(this.localClassName,"onNext: $it")
                runOnUiThread{
                    binding.tvTest.text = it.toString()
                }
            },
            {
                Log.i(this.localClassName,"onError: $it")
            },
            {
                val point = Point(10, 10)
                Log.i(this.localClassName,"onCompleted ${point}")
            }
        )
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    private fun timerOperator(): Observable<Long> {
        return Observable.timer(5, TimeUnit.SECONDS)
    }

    private fun intervalOperator(): Observable<Long> {
        return Observable.interval(1, 1, TimeUnit.SECONDS).takeWhile{
                value -> value <= 10
        }
    }
}


