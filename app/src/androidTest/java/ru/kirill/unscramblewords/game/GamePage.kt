package ru.kirill.unscramblewords.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.kirill.unscramblewords.R

class GamePage(
    private val unscrambleWord: String
) {

    private val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
    val containerIdMatcher: Matcher<View> =
        isDescendantOfA(withId(R.id.game_container))
//    val containerClassMatcher = withParent(isAssignableFrom(LinearLayout::class.java))
    private val failUiText = FailUiText(text = context.getString(R.string.failure), containerIdMatcher)
    private val correctUiText =
        CorrectUiText(text = context.getString(R.string.correct), containerIdMatcher)
    private val unscrambleWordUInt = UnscrambleWordUi(
        text = unscrambleWord,
        containerIdMatcher = containerIdMatcher
    )
    private val inputUi = InputUi(
        containerIdMatcher = containerIdMatcher,
    )
    private val checkButtonUi = CheckButtonUi(
        id = R.id.checkButton,
        textResId = R.string.check,
        containerIdMatcher = containerIdMatcher,
    )
    private val nextButtonUi = ButtonUi(
        id = R.id.nextButton,
        colorHex = "#279C96",
        textResId = R.string.next,
        containerIdMatcher = containerIdMatcher,
    )
    private val skipButtonUi = ButtonUi(
        id = R.id.skipButton,
        colorHex = "#279C96",
        textResId = R.string.skip,
        containerIdMatcher = containerIdMatcher,
    )

    fun checkInitialState() {
        failUiText.assertNotVisible()
        correctUiText.assertNotVisible()
        unscrambleWordUInt.assertTextIsDisplayed()
        inputUi.assertInputFieldIsEmpty()
        checkButtonUi.assertNotClickable()
        nextButtonUi.assertNotVisible()
        skipButtonUi.assertVisible()
    }

    fun input(word: String) {
        inputUi.inputText(word)
    }

    fun checkInputVariantStateIsCheckAvailable() {
        failUiText.assertNotVisible()
        correctUiText.assertNotVisible()
        unscrambleWordUInt.assertTextIsDisplayed()
        inputUi.assertInputFieldIsNotEmpty()
        checkButtonUi.assertClickable()
        nextButtonUi.assertNotVisible()
        skipButtonUi.assertVisible()
    }

    fun checkInputVariantStateIsCheckUnavailable() {
        failUiText.assertNotVisible()
        correctUiText.assertNotVisible()
        unscrambleWordUInt.assertTextIsDisplayed()
        inputUi.assertInputFieldIsNotEmpty()
        checkButtonUi.assertNotClickable()
        nextButtonUi.assertNotVisible()
        skipButtonUi.assertVisible()
    }

    fun clickCheckButton() {
        checkButtonUi.click()
    }

    fun checkCorrectState() {
        failUiText.assertNotVisible()
        correctUiText.assertVisible()
        unscrambleWordUInt.assertTextIsDisplayed()
        inputUi.assertInputFieldIsNotEmpty()
        checkButtonUi.assertNotClickable()
        nextButtonUi.assertVisible()
        skipButtonUi.assertNotVisible()
    }

    fun clickNext() {
        nextButtonUi.click()
    }

    fun clickSkipButton() {
        skipButtonUi.click()
    }

    fun checkIncorrectState() {
        failUiText.assertVisible()
        correctUiText.assertNotVisible()
        unscrambleWordUInt.assertTextIsDisplayed()
        inputUi.assertInputFieldIsNotEmpty()
        checkButtonUi.assertClickable()
        nextButtonUi.assertNotVisible()
        skipButtonUi.assertVisible()
    }

    fun assertNotVisible() {
        inputUi.assertDoesNotExists()
    }
}