package com.rrat.playgroundktxmodulerxjava.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.rrat.playgroundktxmodulerxjava.data.repository.PlaylistRepository

class PlaylistViewModel(private val repository: PlaylistRepository) : ViewModel(){
    val playlists = liveData {
        emitSource(repository.getPlaylists().asLiveData())
    }
}
