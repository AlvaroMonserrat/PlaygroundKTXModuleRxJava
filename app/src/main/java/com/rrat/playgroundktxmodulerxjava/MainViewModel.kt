package com.rrat.playgroundktxmodulerxjava

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.i("TEST","ON CLEARED VIEW MODEL")

    }
}