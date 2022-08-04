package com.rrat.playgroundktxmodulerxjava.data.repository


import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.data.service.PlaylistService
import kotlinx.coroutines.flow.Flow


class PlaylistRepository(private val service: PlaylistService) {
    fun getPlaylists() : Flow<Result<List<Playlist>>> = service.fetchPlaylists()
}