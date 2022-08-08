package com.rrat.playgroundktxmodulerxjava.fortesting.playlist

import com.rrat.playgroundktxmodulerxjava.data.PlaylistDetails
import com.rrat.playgroundktxmodulerxjava.data.repository.PlaylistRepository
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.BaseUnitTest
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.captureValues
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.getValueForTest
import com.rrat.playgroundktxmodulerxjava.viewmodel.PlaylistDetailsViewModel
import com.rrat.playgroundktxmodulerxjava.viewmodel.PlaylistViewModel
import junit.framework.Assert
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

class PlaylistDetailsViewModelShould : BaseUnitTest() {


    private val repository: PlaylistRepository = mock()
    private val playlistDetails = mock<PlaylistDetails>()
    private val expectedSuccess = Result.success(playlistDetails)
    private val exception = RuntimeException("Something went wrong")

    private val id = "1"

    @Test
    fun getPlaylistDetailsFromRepository()= runTest{
        val viewModel = mockSuccessfulCase() //only real object
        viewModel.getPlaylistDetails(id)
        viewModel.playlistDetails.getValueForTest()
        verify(repository, times(1)).getPlaylistDetails(id)
    }

    @Test
    fun emitsPlaylistDetailsFromRepository()= runTest{
        val viewModel = mockSuccessfulCase() //only real object
        viewModel.getPlaylistDetails(id)
        Assert.assertEquals(expectedSuccess, viewModel.playlistDetails.getValueForTest())
    }


    @Test
    fun emitErrorWhenReceiveErrorFromRepository()= runTest {
        val viewModel = mockFailureCase() //only real object
        viewModel.getPlaylistDetails(id)
        Assert.assertEquals(exception, viewModel.playlistDetails.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading()= runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues{
            viewModel.getPlaylistDetails(id)
            viewModel.playlistDetails.getValueForTest()
            Assert.assertEquals(true, values[0])
        }
    }

    @Test
    fun hideLoaderAfterPlaylistsLoad()= runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.getPlaylistDetails(id)
            viewModel.playlistDetails.getValueForTest()
            Assert.assertEquals(false, values.last())
        }
    }


    private fun mockSuccessfulCase(): PlaylistDetailsViewModel {
        runBlocking {
            whenever(repository.getPlaylistDetails(id)).thenReturn(
                flow {
                    emit(expectedSuccess)
                }
            )
        }

        return PlaylistDetailsViewModel(repository)
    }

    private fun mockFailureCase(): PlaylistDetailsViewModel {
        runBlocking {
            whenever(repository.getPlaylistDetails(id)).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }

        return PlaylistDetailsViewModel(repository)
    }
}