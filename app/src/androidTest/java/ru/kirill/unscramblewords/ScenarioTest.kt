package ru.kirill.unscramblewords

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import ru.kirill.unscramblewords.game.GamePage

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun init() {
        gamePage = GamePage(
            unscrambleWord = "htacw"
        )
    }

    @Test
    fun testCase1() {
        gamePage.checkInitialState()

        gamePage.input("watch")
        gamePage.checkInputVariantStateIsCheckAvailable()

        gamePage.clickCheckButton()
        gamePage.checkCorrectState()

        gamePage.clickNext()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase2() {

        gamePage.checkInitialState()

        gamePage.input("wacth")
        gamePage.checkInputVariantStateIsCheckAvailable()

        gamePage.clickCheckButton()
        gamePage.checkIncorrectState()

        gamePage.input("watch")
        gamePage.clickCheckButton()
        gamePage.checkCorrectState()

        gamePage.clickNext()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase3() {

        gamePage.checkInitialState()

        gamePage.input("wat") // input < char then right answer
        gamePage.checkInputVariantStateIsCheckUnavailable() // check Is unavailable
    }

    @Test
    fun testCase4() {

        gamePage.checkInitialState()

        gamePage.input("watch") // input == char right answer
        gamePage.checkInputVariantStateIsCheckAvailable() // check Is available
    }

    @Test
    fun testCase5() {

        gamePage.checkInitialState()

        gamePage.clickSkipButton()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase6() {

        gamePage.checkInitialState()

        gamePage.input("watch") // input == char right answer
        gamePage.checkInputVariantStateIsCheckAvailable() // check Is available

        gamePage.clickSkipButton()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase7() {

        gamePage.checkInitialState()

        gamePage.input("wathc") // input == char right answer
        gamePage.checkInputVariantStateIsCheckAvailable() // check Is available

        gamePage.clickCheckButton()
        gamePage.checkIncorrectState()

        gamePage.clickSkipButton()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }
}