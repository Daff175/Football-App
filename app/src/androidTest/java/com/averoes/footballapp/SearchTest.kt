package com.averoes.footballapp


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.KeyEvent
import com.averoes.footballapp.mvp.view.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        Thread.sleep(3000)
        onView(withId(R.id.sear_match)).check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(R.id.sear_match)).perform(typeText("test ever"), pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(300)
        onView(withId(R.id.sear_match)).perform(typeText("ton"), pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(4000)



    }
}
