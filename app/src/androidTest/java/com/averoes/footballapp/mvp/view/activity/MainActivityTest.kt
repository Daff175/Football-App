package com.averoes.footballapp.mvp.view.activity


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.KeyEvent
import com.averoes.footballapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        Thread.sleep(3000)
        onView(withId(R.id.search)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.sear_match)).check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(R.id.sear_match)).perform(typeText("test ever"), pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(300)
        onView(withId(R.id.sear_match)).perform(typeText("ton"), pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(4000)



    }
}
