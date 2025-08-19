package ru.kirill.unscramblewords

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testCase1() {
        var gamePage = gamePage(
            unscrambleWord = "htacw"
        )

        gamePage.checkInitialState()

        gamePage.input()
        gamePage.checkInputVariantState()

        gamePage.clickCheckButton()
        gamePage.checkCorrectState()

        gamePage.clickNext()

        gamePage = gamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase2() {
        val gamePage = gamePage(
            unscrambleWord = "htacw"
        )

        gamePage.checkInitialState()

        gamePage.input()
        gamePage.checkInputVariantState()

        gamePage.clickCheckButton()
        gamePage.checkIncorrectState()

        gamePage.input()
        gamePage.checkCorrectState()

        gamePage.clickNext()

        gamePage = gamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase3() {
        val gamePage = gamePage(
            unscrambleWord = "htacw"
        )

        gamePage.checkInitialState()

        gamePage.input() // input < char then right answer
        gamePage.checkInputVariantState() // check Is unavailable
    }

    @Test
    fun testCase4() {
        val gamePage = gamePage(
            unscrambleWord = "htacw"
        )

        gamePage.checkInitialState()

        gamePage.input() // input == char right answer
        gamePage.checkInputVariantState() // check Is available
    }

    @Test
    fun testCase5() {
        val gamePage = gamePage(
            unscrambleWord = "htacw"
        )

        gamePage.checkInitialState()

        gamePage.clickSkipButton()

        gamePage = gamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase6() {
        val gamePage = gamePage(
            unscrambleWord = "htacw"
        )

        gamePage.checkInitialState()

        gamePage.input() // input == char right answer
        gamePage.checkInputVariantState() // check Is available

        gamePage.clickSkipButton()

        gamePage = gamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase7() {
        val gamePage = gamePage(
            unscrambleWord = "htacw"
        )

        gamePage.checkInitialState()

        gamePage.input() // input == char right answer
        gamePage.checkInputVariantState() // check Is available

        gamePage.clickCheckButton()
        gamePage.checkIncorrectState()

        gamePage.clickSkipButton()

        gamePage = gamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }
}