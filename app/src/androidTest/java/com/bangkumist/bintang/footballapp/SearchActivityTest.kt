package com.bangkumist.bintang.footballapp

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.bangkumist.bintang.footballapp.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.action.ViewActions.typeText
import android.widget.EditText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.support.test.espresso.action.ViewActions.clearText
import android.support.test.espresso.Espresso.onView
import com.bangkumist.bintang.footballapp.activity.SearchMatchActivity


@RunWith(AndroidJUnit4::class)
class SearchActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(SearchMatchActivity::class.java)


    @Test
    fun TestSearch(){
        onView(ViewMatchers.withId(search_src_text)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("Barcelona"))
        Thread.sleep(2000)
        onView(withId(rcSearch))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(200)
        onView(withText("Barcelona"))
            .check(matches(isDisplayed()))
        pressBack()

        onView(isAssignableFrom(EditText::class.java)).perform(clearText())

        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("Chelsea"))
        Thread.sleep(2000)
        onView(withId(rcSearch))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(200)
        onView(withText("Chelsea"))
            .check(matches(isDisplayed()))
        pressBack()

        onView(isAssignableFrom(EditText::class.java)).perform(clearText())

        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("Real Madrid"))
        Thread.sleep(2000)
        onView(withId(rcSearch))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(200)
        onView(withText("Real Madrid"))
            .check(matches(isDisplayed()))
    }
}