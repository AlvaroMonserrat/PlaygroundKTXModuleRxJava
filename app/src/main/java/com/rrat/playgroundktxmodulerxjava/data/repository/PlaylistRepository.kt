package com.rrat.playgroundktxmodulerxjava.data.repository


import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.data.model.PlaylistMapper
import com.rrat.playgroundktxmodulerxjava.data.service.PlaylistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PlaylistRepository @Inject constructor(
    private val service: PlaylistService,
    private val mapper: PlaylistMapper) {

    fun getPlaylists() : Flow<Result<List<Playlist>>> =
        service.fetchPlaylists().map{
            if(it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)
    }
}