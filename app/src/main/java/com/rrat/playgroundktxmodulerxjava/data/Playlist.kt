package com.rrat.playgroundktxmodulerxjava.data

import com.rrat.playgroundktxmodulerxjava.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist
)