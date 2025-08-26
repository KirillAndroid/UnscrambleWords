package ru.kirill.unscramblewords.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

class FailUiText(
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassMatcher: Matcher<View>
) {

    private val interaction: ViewInteraction = onView(
        org.hamcrest.Matchers.allOf(
            androidx.test.espresso.matcher.ViewMatchers.withText(text),
            containerIdMatcher,
            containerClassMatcher,
        )
    )
    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

}
