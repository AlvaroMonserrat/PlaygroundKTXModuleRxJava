package com.rrat.playgroundktxmodulerxjava.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrat.playgroundktxmodulerxjava.R
import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.viewmodel.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private val viewModel: PlaylistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        viewModel.playlists.observe(this as LifecycleOwner)
        {
            playlists->
            if(playlists.getOrNull() != null)
            {
                setupList(view, playlists.getOrNull()!!)
            }else{
                //TODO
            }

        }

        return view
    }

    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlists)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }
}