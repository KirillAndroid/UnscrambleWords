package ru.kirill.unscramblewords.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
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
    classTypeMatcher: Matcher<View>
) {

    private val layoutInteraction: ViewInteraction = onView(
        org.hamcrest.Matchers.allOf(
            withId(R.id.text_input_layout),
            containerIdMatcher,
            classTypeMatcher,
            isAssignableFrom(TextInputLayout::class.java)
        )
    )

    private val inputInteraction: ViewInteraction = onView(
        org.hamcrest.Matchers.allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.text_input_edit_text),
            withParent( withId(R.id.text_input_layout)),
            withParent(isAssignableFrom(TextInputLayout::class.java)),
        )
    )
    fun assertInputFieldIsEmpty() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(true)))
        inputInteraction.check(matches(withText("")))

    }

    fun inputText(word: String) {
        inputInteraction.perform(androidx.test.espresso.action.ViewActions.replaceText(word))
    }

    fun assertInputFieldIsNotEmpty() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(true)))
        inputInteraction.check(matches(withText(org.hamcrest.Matchers.not(""))))
    }

}
