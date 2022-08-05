package com.rrat.playgroundktxmodulerxjava.data.service

import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.data.PlaylistRaw
import retrofit2.http.GET


interface PlaylistAPI {
    @GET("playlists")
    suspend fun fetchAllPlaylists() : List<PlaylistRaw>
}