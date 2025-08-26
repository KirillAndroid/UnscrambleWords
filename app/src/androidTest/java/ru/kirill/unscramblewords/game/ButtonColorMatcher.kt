package ru.kirill.unscramblewords.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import kotlin.jvm.java

class ButtonColorMatcher(private val color: Int)
    : BoundedMatcher<View, Button>(Button::class.java) {

    constructor(color: String) : this(Color.parseColor(color))

    override fun describeTo(description: Description?) {
        description?.appendText("with background color: $color")
    }
    override fun matchesSafely(item: Button?): Boolean {
        if (item == null) return false
        val buttonColor = (item.background as? ColorDrawable)?.color
        return buttonColor == color
    }
}