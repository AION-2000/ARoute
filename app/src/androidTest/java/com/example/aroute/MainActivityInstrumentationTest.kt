package com.example.aroute

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.aroute", appContext.packageName)
    }

    @Test
    fun testLaunch() {
        // Test that the activity launches successfully
        onView(withId(R.id.container)).check(matches(isDisplayed()))
    }

    @Test
    fun testClearButton() {
        // Test that the clear button is displayed and clickable
        onView(withId(R.id.clear_button))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    @Test
    fun testMenu() {
        // Test that the settings menu item is displayed
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText("Settings")).check(matches(isDisplayed()))
    }
}