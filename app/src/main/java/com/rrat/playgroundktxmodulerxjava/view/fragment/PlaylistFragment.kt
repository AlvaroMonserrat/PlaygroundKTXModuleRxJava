package com.rrat.playgroundktxmodulerxjava.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrat.playgroundktxmodulerxjava.R
import com.rrat.playgroundktxmodulerxjava.data.Playlist
import com.rrat.playgroundktxmodulerxjava.databinding.FragmentPlaylistBinding
import com.rrat.playgroundktxmodulerxjava.viewmodel.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private val viewModel: PlaylistViewModel by viewModels()

    private lateinit var binding: FragmentPlaylistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)

        viewModel.loader.observe(this as LifecycleOwner) { loading->
            when(loading){
                true-> binding.loader.visibility = View.VISIBLE
                else-> binding.loader.visibility = View.GONE
            }
        }

        viewModel.playlists.observe(this as LifecycleOwner)
        {
            playlists->

            if(playlists.getOrNull() != null)
            {
                setupList(playlists.getOrNull()!!)
            }else{
                //TODO
            }

        }

        return binding.root
    }

    private fun setupList(playlists: List<Playlist>) {
        binding.playlistsList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.playlistsList.adapter = MyPlaylistRecyclerViewAdapter(playlists){
            id->
            val action = PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(id)
            findNavController().navigate(action)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }
}