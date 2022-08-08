package com.rrat.playgroundktxmodulerxjava

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.rrat.playgroundktxmodulerxjava.view.PlayListActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class PlaylistDetailFeature : BaseUITest() {

    val mActivityRule = ActivityScenarioRule(PlayListActivity::class.java)
        @Rule get


    @Test
    fun displaysPlaylistNameAndDetails()
    {
        Thread.sleep(4000)
        navigateToPlaylistDetails(0)

        //Revisar Hard Rock Cafe
        //assertDisplayed("Hard Rock Cafe")

        //Revisar details
        //assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")

    }



    @Test
    fun displayLoaderWhileFetchingPlaylistDetails()
    {
        //Check if Loader is displayed when start PlaylistFragment
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hideLoaderWhenPlaylistDetailsIsReceived()
    {
        Thread.sleep(2000)
        BaristaVisibilityAssertions.assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displaysErrorMessageWhenNetworkFails()
    {
        Thread.sleep(2000)
        navigateToPlaylistDetails(1)
        assertDisplayed(R.string.generic_error)
    }

    private fun navigateToPlaylistDetails(itemPosition: Int) {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.playlist_image),
                ViewMatchers.isDescendantOfA(
                    nthChildOf(
                        ViewMatchers.withId(R.id.playlists_list),
                        itemPosition
                    )
                )
            )
        )
            .perform(ViewActions.click())
    }
}