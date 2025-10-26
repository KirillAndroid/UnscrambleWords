package ru.kirill.unscramblewords.fragments.game

import android.view.View
import ru.kirill.unscramblewords.customviews.InputView
import ru.kirill.unscramblewords.customviews.InputViewState
import ru.kirill.unscramblewords.customviews.UnscrambleTextView
import ru.kirill.unscramblewords.customviews.VisibilityButton
import ru.kirill.unscramblewords.customviews.VisibilityButtonState
import ru.kirill.unscramblewords.customviews.VisibilityText
import ru.kirill.unscramblewords.customviews.VisibilityTextState
import ru.kirill.unscramblewords.fragments.stats.NavigateToStats
import java.io.Serializable

interface GameUiState : Serializable {
    fun update(
        inputTextView: InputView,
        failTextView: VisibilityText,
        correctTextView: VisibilityText,
        unscrambleWord: UnscrambleTextView,
        checkButton: VisibilityButton,
        nextButton: VisibilityButton,
        skipButton: VisibilityButton
    ) = Unit

    fun navigate(navigate: NavigateToStats) = Unit

    data class Initial(val unscrambleWordText: String, val userInput: String = "") : GameUiState {
        override fun update(inputTextView: InputView,
                            failTextView: VisibilityText,
                            correctTextView: VisibilityText,
                            unscrambleWord: UnscrambleTextView,
                            checkButton: VisibilityButton,
                            nextButton: VisibilityButton,
                            skipButton: VisibilityButton
        ) {
            unscrambleWord.update(unscrambleWordText)
            inputTextView.update(InputViewState(userInput))


            checkButton.update(VisibilityButtonState(View.VISIBLE, false))
            nextButton.update(VisibilityButtonState(View.GONE, false))
            skipButton.update(VisibilityButtonState(View.VISIBLE, true))

            failTextView.update(VisibilityTextState(View.GONE, false))
            correctTextView.update(VisibilityTextState(View.GONE, false))

        }

    }

    data class InputVariant(
                            val userInput: String,
                            val isCheckAvailable: Boolean) :
        GameUiState {
        override fun update(
            inputTextView: InputView,
            failTextView: VisibilityText,
            correctTextView: VisibilityText,
            unscrambleWord: UnscrambleTextView,
            checkButton: VisibilityButton,
            nextButton: VisibilityButton,
            skipButton: VisibilityButton
        ) {
            inputTextView.update(InputViewState(userInput))
            checkButton.update(VisibilityButtonState(View.VISIBLE, isCheckAvailable))

//            nextButton.update(VisibilityButtonState(View.GONE, false))

//            skipButton.update(VisibilityButtonState(View.VISIBLE, true))
        }

    }

    data class Correct(
        val answer: String,
        val isNextAvailable: Boolean,
        val isCheckAvailable: Boolean,
        val isSkipAvailable: Boolean,
    ) : GameUiState {
        override fun update(
            inputTextView: InputView,
            failTextView: VisibilityText,
            correctTextView: VisibilityText,
            unscrambleWord: UnscrambleTextView,
            checkButton: VisibilityButton,
            nextButton: VisibilityButton,
            skipButton: VisibilityButton
        ) {
            inputTextView.update(InputViewState(answer))

            checkButton.update(VisibilityButtonState(View.VISIBLE, false))

            nextButton.update(VisibilityButtonState(View.VISIBLE, isNextAvailable))

            skipButton.update(VisibilityButtonState(View.GONE, isSkipAvailable))

            failTextView.update(VisibilityTextState(View.GONE, false))
            correctTextView.update(VisibilityTextState(View.VISIBLE, false))

        }

    }

    data class Incorrect(
        val answer: String,
        val isNextAvailable: Boolean,
        val isCheckAvailable: Boolean,
        val isSkipAvailable: Boolean
    ) : GameUiState {
        override fun update(
            inputTextView: InputView,
            failTextView: VisibilityText,
            correctTextView: VisibilityText,
            unscrambleWord: UnscrambleTextView,
            checkButton: VisibilityButton,
            nextButton: VisibilityButton,
            skipButton: VisibilityButton
        ) {
            inputTextView.update(InputViewState(answer))

            checkButton.update(VisibilityButtonState(View.VISIBLE, isCheckAvailable))

            nextButton.update(VisibilityButtonState(View.GONE, isNextAvailable))

            skipButton.update(VisibilityButtonState(View.VISIBLE, isSkipAvailable))


            failTextView.update(VisibilityTextState(View.VISIBLE, false))
            correctTextView.update(VisibilityTextState(View.GONE, false))

        }

    }

    object Empty : GameUiState {

    }

    object Finish : GameUiState {
        override fun navigate(navigate: NavigateToStats) {
            navigate.navigateToStats()
        }
    }

}
