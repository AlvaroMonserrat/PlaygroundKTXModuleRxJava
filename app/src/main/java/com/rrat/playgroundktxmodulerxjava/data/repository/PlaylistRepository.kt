package com.rrat.playgroundktxmodulerxjava.data.repository


import com.rrat.playgroundktxmodulerxjava.data.Playlist
import kotlinx.coroutines.flow.Flow


class PlaylistRepository {
    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> {
        TODO()
    }
}