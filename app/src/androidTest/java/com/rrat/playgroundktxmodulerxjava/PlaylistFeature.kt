package com.rrat.playgroundktxmodulerxjava

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import com.rrat.playgroundktxmodulerxjava.view.PlayListActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test


class PlaylistFeature: BaseUITest() {

   val mActivityRule = ActivityScenarioRule(PlayListActivity::class.java)
    @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlaylists(){
        Thread.sleep(2000)
        assertRecyclerViewItemCount(R.id.playlists_list, 10)

        onView(allOf(withId(R.id.playlist_name), isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_category), isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 1))))
            .check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayRockImageInPlayListWhenCategoryIsRock()
    {
        Thread.sleep(2000)
        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))))
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 3))))
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
    }


    @Test
    fun displayLoaderWhileFetchingThePlaylists()
    {
        //Check if Loader is displayed when start PlaylistFragment
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hideLoaderWhenPlaylistsIsReceived()
    {
        Thread.sleep(2000)
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun navigateToDetailsScreen()
    {
        Thread.sleep(2000)
        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))))
            .perform(click())

        assertDisplayed(R.id.playlists_details_root)
    }

}

