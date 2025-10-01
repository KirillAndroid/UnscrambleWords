package ru.kirill.unscramblewords.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Matcher
import ru.kirill.unscramblewords.R

class InputUi(
    containerIdMatcher: Matcher<View>,
) {

    private val layoutInteraction: ViewInteraction = onView(
        org.hamcrest.Matchers.allOf(
            withId(R.id.text_input_layout),
            containerIdMatcher,
            isAssignableFrom(TextInputLayout::class.java)
        )
    )

    private val inputInteraction: ViewInteraction = onView(
        org.hamcrest.Matchers.allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.text_input_edit_text),
            ViewMatchers.isDescendantOfA( withId(R.id.text_input_layout)),
        )
    )
    fun assertInputFieldIsEmpty() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(false)))
        inputInteraction.check(matches(withText("")))

    }

    fun inputText(word: String) {
        inputInteraction.perform(androidx.test.espresso.action.ViewActions.replaceText(word))
    }

    fun assertInputFieldIsNotEmpty() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(false)))
        inputInteraction.check(matches(withText(org.hamcrest.Matchers.not(""))))
    }

    fun assertDoesNotExists() {
        layoutInteraction.check(doesNotExist())
    }

}
