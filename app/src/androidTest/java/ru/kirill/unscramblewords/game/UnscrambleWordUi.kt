package ru.kirill.unscramblewords.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import ru.kirill.unscramblewords.R

class UnscrambleWordUi(
    text: String,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) {

    private val interaction: ViewInteraction = onView(
        org.hamcrest.Matchers.allOf(
            withId(R.id.unscramble_word_text_view),
            androidx.test.espresso.matcher.ViewMatchers.withText(text),
            containerIdMatcher,
            classTypeMatcher,
        )
    )
    fun assertTextIsDisplayed() {
        interaction.check(matches(isDisplayed()))
    }

}
