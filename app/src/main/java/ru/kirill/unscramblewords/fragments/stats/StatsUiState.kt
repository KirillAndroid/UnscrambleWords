package ru.kirill.unscramblewords.fragments.stats

import android.view.View
import ru.kirill.unscramblewords.R
import ru.kirill.unscramblewords.customviews.StatsTextView
import ru.kirill.unscramblewords.customviews.StatsTextViewState
import ru.kirill.unscramblewords.customviews.UnscrambleTextView
import ru.kirill.unscramblewords.customviews.VisibilityButton
import ru.kirill.unscramblewords.customviews.VisibilityButtonState
import java.io.Serializable

interface StatsUiState : Serializable {

    fun update(textView: StatsTextView, newGameButton: VisibilityButton)

    class Base (
        val correct: Int,
        val incorrect: Int
        ) : StatsUiState {
        override fun update(textView: StatsTextView, newGameButton: VisibilityButton) {
            textView.update(StatsTextViewState(correct, incorrect))
            newGameButton.update(VisibilityButtonState(View.VISIBLE, true))
        }
    }
}
