package com.rrat.playgroundktxmodulerxjava.viewmodel

import androidx.lifecycle.*
import com.rrat.playgroundktxmodulerxjava.data.PlaylistDetails
import com.rrat.playgroundktxmodulerxjava.data.repository.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor(private val repository: PlaylistRepository) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    var playlistDetails: LiveData<Result<PlaylistDetails>> = MutableLiveData()

    fun getPlaylistDetails(id: String)
    {
        playlistDetails = liveData {
            loader.postValue(true)
            emitSource(repository.getPlaylistDetails(id)
                .onEach {
                    loader.postValue(false)
                }
                .asLiveData())
        }
    }
}