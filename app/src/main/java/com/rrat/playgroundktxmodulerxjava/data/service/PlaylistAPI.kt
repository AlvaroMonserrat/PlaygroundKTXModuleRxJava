package com.rrat.playgroundktxmodulerxjava.data.service

import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.data.PlaylistDetails
import com.rrat.playgroundktxmodulerxjava.data.PlaylistRaw
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PlaylistAPI {
    @GET("playlists")
    suspend fun fetchAllPlaylists() : List<PlaylistRaw>

    @GET("playlist-details/{id}")
    suspend fun fetchPlaylistDetails(@Path("id") id: String) : PlaylistDetails
}