package ru.kirill.unscramblewords.game

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description

class TextInputLayoutErrorEnabledMatcher(private val expectedEnabled: Boolean) : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("with error enabled $expectedEnabled")
    }

    override fun matchesSafely(item: TextInputLayout): Boolean {
        return item.isErrorEnabled == expectedEnabled
    }
}