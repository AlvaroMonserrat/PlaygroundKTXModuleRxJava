package com.rrat.playgroundktxmodulerxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.rrat.playgroundktxmodulerxjava.databinding.ActivityPlayBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.collectLatest

class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding

    //private var disposable: Disposable? = null

    private var playViewModel: PlayViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("TEST","ON CREATE PLAY ACTIVITY")

        playViewModel = ViewModelProvider(this)[PlayViewModel::class.java]

        /*if(playViewModel?.backgroundLiveData?.value == null)
        {
            playViewModel?.getDataFromRxJava()
        }
        */
     /*   if(playViewModel?.backgroundStateFlow?.value == null)
        {
            playViewModel?.getDataFromCoroutine()
        }*/
        playViewModel?.getDataFromCoroutine()

        lifecycleScope.launchWhenStarted {
            playViewModel?.backgroundStateFlow?.collect {
                    value -> binding.tvTest.text = value.toString()
            }
        }
/*
        playViewModel?.backgroundLiveData?.observe(this){
            value -> binding.tvTest.text = value.toString()
        }
*/


    }

    private fun runRxJvTest()
    {
        /*  disposable = createObservableRange()
            .subscribeOn(Schedulers.io())
            .subscribe(
            {
                runOnUiThread { binding.tvTest.text = it.toString() }
                Log.i("TEST", "Value $it")
            },
            {
                Log.i("TEST", "ERROR ${it.message}")
            },
            {
                Log.i("TEST", "Completed")
            }

        )*/
    }


    override fun onResume() {
        super.onResume()
        Log.i("TEST","ON RESUME PLAY ACTIVITY")
    }

    override fun onDestroy() {
        //disposable?.dispose()
       // compositeDisposable.clear()
        Log.i("TEST","ON DESTROY PLAY ACTIVITY")
        super.onDestroy()
    }
}