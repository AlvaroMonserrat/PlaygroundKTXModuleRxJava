package com.rrat.playgroundktxmodulerxjava.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.rrat.playgroundktxmodulerxjava.R
import com.rrat.playgroundktxmodulerxjava.data.PlaylistDetails
import com.rrat.playgroundktxmodulerxjava.databinding.FragmentPlaylistDetailBinding
import com.rrat.playgroundktxmodulerxjava.viewmodel.PlaylistDetailsViewModel
import com.rrat.playgroundktxmodulerxjava.viewmodel.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistDetailBinding

    private val viewModel: PlaylistDetailsViewModel by viewModels()

    private val args: PlaylistDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistDetailBinding.inflate(layoutInflater, container, false)

        viewModel.loader.observe(this as LifecycleOwner) { loading->
            when(loading){
                true-> binding.loader.visibility = View.VISIBLE
                else-> binding.loader.visibility = View.GONE
            }
        }

        val id = args.playlistId
        viewModel.getPlaylistDetails(id)

        observePlaylistDetails()

        return binding.root
    }

    private fun observePlaylistDetails() {
        viewModel.playlistDetails.observe(this as LifecycleOwner)
        { playlistDetails ->
            if (playlistDetails.isSuccess)
            {
                if (playlistDetails.getOrNull() != null) {
                    setupUI(playlistDetails)
                }
            }else{
                Snackbar.make(binding.root, R.string.generic_error, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupUI(playlistDetails: Result<PlaylistDetails>) {
        binding.playlistDetailName.text = playlistDetails.getOrNull()!!.name
        binding.playlistDetailDetails.text = playlistDetails.getOrNull()!!.details
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistDetailsFragment()
    }
}