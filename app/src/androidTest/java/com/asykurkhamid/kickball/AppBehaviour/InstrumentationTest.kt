package com.asykurkhamid.kickball.AppBehaviour

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.asykurkhamid.kickball.activity.MainActivity
import com.asykurkhamid.kickball.R.id.*
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentationTest {
    private var mActivity: MainActivity? = null

    @Rule @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        mActivity = activityRule.activity
        assertThat(mActivity, notNullValue())
    }

    @Test
    fun testAllBehaviour(){

        //access lasmatch page
        Thread.sleep(2000)
        onView(withId(rvLastMatch)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(rvLastMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rvLastMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8,click()))// select item

        //click btn favorite
        Thread.sleep(2000)
        onView(withId(btnFavorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(btnFavorite)).perform(click())
        pressBack()

        //to events match fragment
        Thread.sleep(2000)
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_next)).perform(click())

        //select item in events match
        Thread.sleep(2000)
        onView(withId(rvNextMatch)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(rvNextMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rvNextMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8,click()))// select item

        //click btn favorite
        Thread.sleep(2000)
        onView(withId(btnFavorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(btnFavorite)).perform(click())
        pressBack()

        //to Favorite fragment
        Thread.sleep(2000)
        onView(withId(navigation_favorite)).perform(click())

        //select item in favorite events match
        Thread.sleep(2000)
        onView(withId(rvFavLastMatch)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(rvFavLastMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rvFavLastMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))// select item

        //click btn favorite
        Thread.sleep(2000)
        onView(withId(btnFavorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(btnFavorite)).perform(click())
        pressBack()

        //swipe to favorite events match
        Thread.sleep(2000)
        onView(withId(vpFav)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(vpFav)).perform(swipeLeft())

        onView(withId(tabFav)).check(ViewAssertions.matches(isDisplayed()))
        onView(allOf(withText("Next Match"), isDescendantOfA(withId(tabFav))))
                .perform(click())
                .check(matches(isDisplayed()))

        //select item in favorite events match
        onView(withId(rvFavNextMatch)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(rvFavNextMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rvFavNextMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))// select item

        //click btn favorite
        Thread.sleep(2000)
        onView(withId(btnFavorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(btnFavorite)).perform(click())
        pressBack()

        Thread.sleep(2000)

    }
}