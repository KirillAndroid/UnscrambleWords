package ru.kirill.unscramblewords.customviews

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import ru.kirill.unscramblewords.R

class StatsTextView : AppCompatTextView, UpdateStatsTextView {
    private lateinit var state: StatsTextViewState
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val StatsTextViewStateSaved = StatsTextViewStateSaved(it)
            StatsTextViewStateSaved.save(state)
            return StatsTextViewStateSaved
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as StatsTextViewStateSaved
        super.onRestoreInstanceState(savedState.superState)
//        update(savedState.restore())
    }

    override fun update(correct: Int, incorrect: Int) {
        val text = "Statistics: \nCorrect = $correct, \nIncorrect = $incorrect"
        this.text = text
//        text = resources.getString(R.string.statistics, correct, incorrect)
    }

    override fun update(state: StatsTextViewState) {
        this.state = state
        state.update(this)
    }

}

interface UpdateStatsTextView {
    fun update(correct: Int, incorrect: Int)
    fun update(state: StatsTextViewState)
}