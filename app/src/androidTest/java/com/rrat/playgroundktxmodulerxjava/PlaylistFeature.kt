package com.rrat.playgroundktxmodulerxjava

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.rrat.playgroundktxmodulerxjava.view.MainActivity
import com.rrat.playgroundktxmodulerxjava.view.PlayListActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

   val mActivityRule = ActivityScenarioRule(PlayListActivity::class.java)
    @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }
}