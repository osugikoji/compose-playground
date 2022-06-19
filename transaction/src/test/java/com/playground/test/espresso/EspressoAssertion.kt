package com.playground.test.espresso

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

object EspressoAssertion {

    fun checkText(@IdRes id: Int, text: String): ViewInteraction {
        return onView(withId(id)).check(matches(withText(text)))
    }

    fun isVisible(text: String): ViewInteraction {
        return onView(withText(text)).check(matches(isDisplayed()))
    }
}
