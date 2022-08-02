package com.rrat.playgroundktxmodulerxjava.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rrat.playgroundktxmodulerxjava.viewmodel.MainViewModel
import com.rrat.playgroundktxmodulerxjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("TEST","ON CREATE MAIN ACTIVITY")
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }

        liveDataPlayGround()

    }

    private fun liveDataPlayGround()
    {
        /*------LiveData MutableData------*/
        mainViewModel?.liveData?.observe(this)
        {
            binding.tvLiveData.text = it.toString()
        }

        binding.btnLiveData.setOnClickListener {
            mainViewModel?.setLiveData(50)
        }
        /*---------------------------------*/

        /*------LiveData one-shot------*/
        binding.btnLiveDataOneShot.setOnClickListener {
            mainViewModel?.getOneShotLiveData()
            mainViewModel?.resultLiveData?.observe(this){
                binding.tvLiveDataOneShot.text = it
            }
        }
        /*Alternativa: solucion rotacion pantalla*/
        binding.tvLiveDataOneShot.text = mainViewModel?.resultLiveData?.value

        mainViewModel?.getSpecialNumber()
    }


    override fun onResume() {
        super.onResume()
        Log.i("TEST","ON RESUME MAIN ACTIVITY")
    }

    override fun onDestroy() {
        Log.i("TEST","ON DESTROY MAIN ACTIVITY")
        super.onDestroy()
    }

}


