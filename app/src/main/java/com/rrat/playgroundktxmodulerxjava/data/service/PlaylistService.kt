package com.rrat.playgroundktxmodulerxjava.data.service

import android.util.Log
import com.rrat.playgroundktxmodulerxjava.data.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistService(private val api: PlaylistAPI) {
    fun fetchPlaylists() : Flow<Result<List<Playlist>>> {
        return flow { emit(Result.success(api.fetchAllPlaylists())) }
            .catch {
                it.message?.let { it1 -> Log.i("Error", it1) }
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
    }
}
