package com.rrat.playgroundktxmodulerxjava.fortesting.playlist

import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.data.PlaylistRaw
import com.rrat.playgroundktxmodulerxjava.data.service.PlaylistAPI
import com.rrat.playgroundktxmodulerxjava.data.service.PlaylistService
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.BaseUnitTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlaylistServiceShould : BaseUnitTest(){


    private val api: PlaylistAPI = mock()
    private val playlists: List<PlaylistRaw> = mock()
    private val exception = RuntimeException("Damn backend developers")

    @Test
    fun getPlaylistFromAPI()= runTest{
        val service = PlaylistService(api)
        service.fetchPlaylists().first()
        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem()= runTest {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)

        val service = PlaylistService(api)
        assertEquals(Result.success(playlists), service.fetchPlaylists().first())

    }

    @Test
    fun emitsErrorResultWhenNetWorkFails()= runTest{
        whenever(api.fetchAllPlaylists()).thenThrow(exception)

        val service = PlaylistService(api)
        assertEquals("Something went wrong", service.fetchPlaylists().first().exceptionOrNull()?.message)
    }
}