package com.rrat.playgroundktxmodulerxjava.fortesting.playlist

import com.rrat.playgroundktxmodulerxjava.R
import com.rrat.playgroundktxmodulerxjava.data.PlaylistRaw
import com.rrat.playgroundktxmodulerxjava.data.model.PlaylistMapper
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.BaseUnitTest
import junit.framework.Assert.assertEquals
import org.junit.Test



class PlaylistMapperShould : BaseUnitTest(){

    private val playlistRaw: PlaylistRaw = PlaylistRaw("1", "jhon", "pop")
    private val playlistRawRock: PlaylistRaw = PlaylistRaw("1", "jhon", "rock")
    private val mapper = PlaylistMapper()

    private val playlists = mapper(listOf(playlistRaw))

    private val playlist = playlists[0]
    private val playlistRock = mapper(listOf(playlistRawRock))[0]

    @Test
    fun keepSameId()
    {
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun keepSameName()
    {
        assertEquals(playlistRaw.name, playlist.name)
    }

    @Test
    fun keepSameCategory()
    {
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun mapDefaultImageWhenCategoryNotRock()
    {
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun mapRockImageWhenCategoryIsRock()
    {
        assertEquals(R.mipmap.rock, playlistRock.image)
    }
}