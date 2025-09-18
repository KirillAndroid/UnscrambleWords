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
        activityScenarioRule.scenario.recreate()

        gamePage.input("watch")
        activityScenarioRule.scenario.recreate()
        gamePage.checkInputVariantStateIsCheckAvailable()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        activityScenarioRule.scenario.recreate()
        gamePage.checkCorrectState()

        gamePage.clickNext()
        activityScenarioRule.scenario.recreate()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )
        activityScenarioRule.scenario.recreate()

        gamePage.checkInitialState()
    }

    @Test
    fun testCase2() {

        gamePage.checkInitialState()
        activityScenarioRule.scenario.recreate()

        gamePage.input("wacth")
        activityScenarioRule.scenario.recreate()
        gamePage.checkInputVariantStateIsCheckAvailable()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        gamePage.checkIncorrectState()

        activityScenarioRule.scenario.recreate()

        gamePage.input("watch")
        activityScenarioRule.scenario.recreate()
        gamePage.clickCheckButton()
        gamePage.checkCorrectState()

        gamePage.clickNext()
        activityScenarioRule.scenario.recreate()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        activityScenarioRule.scenario.recreate()
        gamePage.checkInitialState()
    }

    @Test
    fun testCase3() {

        gamePage.checkInitialState()

        gamePage.input("wat") // input < char then right answer
        activityScenarioRule.scenario.recreate()
        gamePage.checkInputVariantStateIsCheckUnavailable() // check Is unavailable
    }

    @Test
    fun testCase4() {

        gamePage.checkInitialState()

        gamePage.input("watch") // input == char right answer
        activityScenarioRule.scenario.recreate()
        gamePage.checkInputVariantStateIsCheckAvailable() // check Is available
    }

    @Test
    fun testCase5() {

        gamePage.checkInitialState()

        gamePage.clickSkipButton()
        activityScenarioRule.scenario.recreate()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        activityScenarioRule.scenario.recreate()
        gamePage.checkInitialState()
    }

    @Test
    fun testCase6() {

        gamePage.checkInitialState()

        gamePage.input("watch") // input == char right answer
        activityScenarioRule.scenario.recreate()
        gamePage.checkInputVariantStateIsCheckAvailable() // check Is available
        activityScenarioRule.scenario.recreate()

        gamePage.clickSkipButton()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }

    @Test
    fun testCase7() {

        gamePage.checkInitialState()
        activityScenarioRule.scenario.recreate()

        gamePage.input("wathc") // input == char right answer
        gamePage.checkInputVariantStateIsCheckAvailable() // check Is available
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheckButton()
        gamePage.checkIncorrectState()

        gamePage.clickSkipButton()

        gamePage = GamePage(
            unscrambleWord = "olhel"
        )

        gamePage.checkInitialState()
    }
}