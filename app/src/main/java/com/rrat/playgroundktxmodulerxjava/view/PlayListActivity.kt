package com.rrat.playgroundktxmodulerxjava.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rrat.playgroundktxmodulerxjava.databinding.ActivityMainBinding
import com.rrat.playgroundktxmodulerxjava.databinding.ActivityPlayListBinding
import com.rrat.playgroundktxmodulerxjava.view.fragment.PlaylistFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null)
        {
            supportFragmentManager
                .beginTransaction()
                .add(binding.container.id, PlaylistFragment.newInstance())
                .commit()
        }
    }

}