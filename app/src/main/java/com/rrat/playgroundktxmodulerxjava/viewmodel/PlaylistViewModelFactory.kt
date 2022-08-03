package com.rrat.playgroundktxmodulerxjava.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rrat.playgroundktxmodulerxjava.data.repository.PlaylistRepository

class PlaylistViewModelFactory(private val repository: PlaylistRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistViewModel(repository) as T
    }
}