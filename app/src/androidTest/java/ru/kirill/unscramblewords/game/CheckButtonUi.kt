package ru.kirill.unscramblewords.game

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class CheckButtonUi(
    id: Int,
    textResId: Int,
    containerIdMatcher: Matcher<View>,
) : AbstractButtonUI(
    onView(
        allOf(
            withId(id),
            withText(textResId),
            containerIdMatcher,
            isAssignableFrom(AppCompatButton::class.java),
        )
    )
) {
    fun assertClickable() {
        interaction.check(matches(isEnabled()))
    }

    fun assertNotClickable() {
        interaction.check(matches(not(isEnabled())))
    }


}
