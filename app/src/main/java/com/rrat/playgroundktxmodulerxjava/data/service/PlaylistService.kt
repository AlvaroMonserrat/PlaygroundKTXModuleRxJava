package com.rrat.playgroundktxmodulerxjava.data.service

import android.util.Log
import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.data.PlaylistDetails
import com.rrat.playgroundktxmodulerxjava.data.PlaylistRaw
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistService @Inject constructor(private val api: PlaylistAPI) {
    fun fetchPlaylists() : Flow<Result<List<PlaylistRaw>>> {
        return flow { emit(Result.success(api.fetchAllPlaylists())) }
            .catch {
                it.message?.let { it1 -> Log.i("Error", it1) }
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
    }

    fun fetchPlaylistDetails(id: String): Flow<Result<PlaylistDetails>>
    {
        return flow { emit(Result.success(api.fetchPlaylistDetails(id))) }
            .catch {
                it.message?.let { it1->Log.i("Error", it1) }
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
    }
}
