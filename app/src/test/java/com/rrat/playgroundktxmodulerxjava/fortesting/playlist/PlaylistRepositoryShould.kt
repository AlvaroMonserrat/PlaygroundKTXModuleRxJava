package com.rrat.playgroundktxmodulerxjava.fortesting.playlist

import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.data.PlaylistDetails
import com.rrat.playgroundktxmodulerxjava.data.PlaylistRaw
import com.rrat.playgroundktxmodulerxjava.data.model.PlaylistMapper
import com.rrat.playgroundktxmodulerxjava.data.repository.PlaylistRepository
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.BaseUnitTest
import com.rrat.playgroundktxmodulerxjava.data.service.PlaylistService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

class PlaylistRepositoryShould : BaseUnitTest() {

    private val service: PlaylistService = mock()
    private val mapper: PlaylistMapper = mock()
    private val playlists = mock<List<Playlist>>()
    private val playlistRaw = mock<List<PlaylistRaw>>()
    private val playlistDetails = mock<PlaylistDetails>()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getsPlaylistsFromService()= runTest{

        val repository = mockSuccessfulCaseGetPlaylist()

        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitMappedPlaylistsFromService()= runTest {
        val repository = mockSuccessfulCaseGetPlaylist()
        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun emitErrorFromService()= runTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }

    @Test
    fun delegateBusinessLogicToMapper()= runTest {
        val repository: PlaylistRepository = mockSuccessfulCaseGetPlaylist()

        repository.getPlaylists().first()

        verify(mapper, times(1)).invoke(playlistRaw)
    }

    @Test
    fun getPlaylistDetails()
    {
        val repository: PlaylistRepository = mockSuccessfulCaseGetPlaylistDetails()

        repository.getPlaylistDetails("1")

        verify(service, times(1)).fetchPlaylistDetails("1")
    }

    private fun mockSuccessfulCaseGetPlaylist(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlistRaw))
            }
        )

        whenever(mapper.invoke(playlistRaw)).thenReturn(playlists)

        return PlaylistRepository(service, mapper)
    }

    private fun mockSuccessfulCaseGetPlaylistDetails(): PlaylistRepository {
        whenever(service.fetchPlaylistDetails("1")).thenReturn(
            flow {
                emit(Result.success(playlistDetails))
            }
        )

        return PlaylistRepository(service, mapper)
    }

    private fun mockFailureCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        return PlaylistRepository(service, mapper)
    }
}