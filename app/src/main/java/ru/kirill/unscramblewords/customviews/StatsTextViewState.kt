package ru.kirill.unscramblewords.customviews

import java.io.Serializable

data class StatsTextViewState(val correct: Int, val incorrect: Int) : Serializable {
    fun update(view: StatsTextView) {
        view.update(correct, incorrect)
    }
}