package com.rrat.playgroundktxmodulerxjava.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rrat.playgroundktxmodulerxjava.data.Playlist

class PlaylistViewModel : ViewModel(){
    val playlists = MutableLiveData<List<Playlist>>()
}
