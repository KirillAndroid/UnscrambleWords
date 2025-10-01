package ru.kirill.unscramblewords.game

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import ru.kirill.unscramblewords.R

class GameOverPage(correct: Int, incorrect: Int) {


    val containerIdMatcher: Matcher<View> =
        isDescendantOfA(withId(R.id.game_over_container))

    private val statistics: StatisticsUi = StatisticsUi(correct = correct, incorrect = incorrect, containerIdMatcher)

    private val newGameButton: ButtonUi = ButtonUi(R.id.newGameButton, "#279C96", R.string.new_game, containerIdMatcher)

    fun assertGameOverState() {
        statistics.assertVisible()
        newGameButton.assertVisible()
    }

    fun clickNewGameButton() {
        newGameButton.click()
    }

    fun assertNotVisible() {
        statistics.assertDoesNotExists()
        newGameButton.assertNotVisible()
    }
}