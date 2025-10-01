package ru.kirill.unscramblewords.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.kirill.unscramblewords.R

class StatisticsUi(
    correct: Int,
    incorrect: Int,
    containerIdMatcher: Matcher<View>
) {
    private val text = "Statistics: \nCorrect = $correct, \nIncorrect = $incorrect"
    private val interaction: ViewInteraction = onView(
        allOf(
            withId(R.id.statistics),
            containerIdMatcher,
            withText(text))
    )

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertDoesNotExists() {
        interaction.check(androidx.test.espresso.assertion.ViewAssertions.doesNotExist())
    }

}
