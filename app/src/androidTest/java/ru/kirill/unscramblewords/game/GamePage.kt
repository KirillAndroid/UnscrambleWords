package ru.kirill.unscramblewords.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.kirill.unscramblewords.R

class GamePage(
    private val unscrambleWord: String
) {
    val containerIdMatcher: Matcher<View> = withParent(withId(R.id.game_container))
    val containerClassMatcher = withParent(isAssignableFrom(LinearLayout::class.java))
    private val failUiText = FailUiText(text = "fail", containerIdMatcher, containerClassMatcher)
    private val correctUiText =
        CorrectUiText(text = "correct", containerIdMatcher, containerClassMatcher)
    private val unscrambleWordUInt = UnscrambleWordUi(
        text = unscrambleWord,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = containerClassMatcher
    )
    private val inputUi = InputUi(
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = containerClassMatcher
    )
    private val checkButtonUi = CheckButtonUi(
        id = R.id.checkButton,
        textResId = R.string.check,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = containerClassMatcher
    )
    private val nextButtonUi = ButtonUi(
        id = R.id.nextButton,
        colorHex = "#279C96",
        textResId = R.string.next,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = containerClassMatcher
    )
    private val skipButtonUi = ButtonUi(
        id = R.id.skipButton,
        colorHex = "#279C96",
        textResId = R.string.skip,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = containerClassMatcher
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
}