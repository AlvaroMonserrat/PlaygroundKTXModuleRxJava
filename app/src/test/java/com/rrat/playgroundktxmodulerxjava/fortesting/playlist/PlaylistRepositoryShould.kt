package com.rrat.playgroundktxmodulerxjava.fortesting.playlist

import com.rrat.playgroundktxmodulerxjava.data.Playlist
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
    private val playlists = mock<List<Playlist>>()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getsPlaylistsFromService()= runTest{

        val repository = PlaylistRepository(service)

        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitPlaylistsFromService()= runTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun emitErrorFromService()= runTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }

    private fun mockSuccessfulCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlists))
            }
        )

        return PlaylistRepository(service)
    }

    private fun mockFailureCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        return PlaylistRepository(service)
    }
}