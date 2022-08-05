package com.rrat.playgroundktxmodulerxjava.fortesting.playlist


import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.data.repository.PlaylistRepository
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.BaseUnitTest
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.captureValues
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.getValueForTest
import com.rrat.playgroundktxmodulerxjava.viewmodel.PlaylistViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

class PlaylistViewModelShould : BaseUnitTest(){

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expectedSuccess = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")
    @Test
    fun getPlaylistsFromRepository()= runTest{
        val viewModel = mockSuccessfulCase() //only real object
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistFromRepository()= runTest{
        val viewModel = mockSuccessfulCase() //only real object
        assertEquals(expectedSuccess, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveErrorFromRepository()= runTest {
        val viewModel = mockFailureCase() //only real object
        assertEquals(exception, viewModel.playlists.getValueForTest()?.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading()= runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues{
            viewModel.playlists.getValueForTest()

            assertEquals(true, values[0])
        }
    }

    @Test
    fun hideLoaderAfterPlaylistsLoad()= runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    @Test
    fun hideLoaderAfterError()= runTest {
        val viewModel = mockFailureCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expectedSuccess)
                }
            )
        }

        return PlaylistViewModel(repository)
    }

    private fun mockFailureCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }

        return PlaylistViewModel(repository)
    }
}