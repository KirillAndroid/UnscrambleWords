package ru.kirill.unscramblewords.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import ru.kirill.unscramblewords.R

class CorrectUiText(
    text: String,
    containerIdMatcher: Matcher<View>,
) {

    private val interaction: ViewInteraction = onView(
        org.hamcrest.Matchers.allOf(
            withId(R.id.correct_text_view),
            androidx.test.espresso.matcher.ViewMatchers.withText(text),
            containerIdMatcher,
        )
    )
    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

}
