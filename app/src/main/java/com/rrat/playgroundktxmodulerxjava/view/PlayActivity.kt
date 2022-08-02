package com.rrat.playgroundktxmodulerxjava.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.rrat.playgroundktxmodulerxjava.viewmodel.PlayViewModel
import com.rrat.playgroundktxmodulerxjava.databinding.ActivityPlayBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        observerChannelFlow()

    }

    private fun observerStateFlow()
    {
        //if(playViewModel?.backgroundStateFlow?.value == null)
        //{
        playViewModel?.getDataFromCoroutineStateFlow()
        //}

        lifecycleScope.launchWhenStarted {

            playViewModel?.backgroundStateFlow?.collect {
                    value -> binding.tvTest.text = value.toString()
            }
        }
    }

    private fun observerSharedFlow()
    {
        playViewModel?.getDataFromCoroutineSharedFlow()

        lifecycleScope.launchWhenStarted {
            playViewModel?.backgroundSharedFlow?.collect { value ->
                binding.tvTest.text = value.toString()
            }
        }
    }

    private fun observerChannelFlow()
    {
        playViewModel?.getDataFromCoroutineChannel()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                Log.i("TEST","CHANNEL STARTS TO BE OBSERVED")
                playViewModel?.flowChannel?.collectLatest { value ->
                    binding.tvTest.text = value.toString()
                }
            }
        }
    }

    private fun observerFromRxJava()
    {
        if(playViewModel?.backgroundLiveData?.value == null)
        {
            playViewModel?.getDataFromRxJava()
        }

        playViewModel?.backgroundLiveData?.observe(this){
                value -> binding.tvTest.text = value.toString()
        }
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

    override fun onStop() {
        super.onStop()
        Log.i("TEST","ON STOP PLAY ACTIVITY")
    }

    override fun onDestroy() {
        //disposable?.dispose()
       // compositeDisposable.clear()
        Log.i("TEST","ON DESTROY PLAY ACTIVITY")
        super.onDestroy()
    }
}