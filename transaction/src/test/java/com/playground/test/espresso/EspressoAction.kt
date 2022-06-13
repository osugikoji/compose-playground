package com.playground.test.espresso

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

object EspressoAction {

    fun typeOnEditText(@IdRes id: Int, text: String): ViewInteraction {
        return onView(withId(id)).perform(typeText(text))
    }

    fun clickOn(@IdRes id: Int): ViewInteraction {
        return onView(withId(id)).perform(click())
    }

    fun clickOn(text: String): ViewInteraction {
        return onView(withText(text)).perform(click())
    }
}
