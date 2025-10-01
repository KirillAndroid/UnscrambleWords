package ru.kirill.unscramblewords.customviews

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class VisibilityText : AppCompatTextView, UpdateVisibility {
    private lateinit var state: VisibilityTextState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

        override fun onSaveInstanceState(): Parcelable? {
            return super.onSaveInstanceState()?.let {
                val VisibilityTextSaved = VisibilityTextSaved(it)
                VisibilityTextSaved.save(state)
                return VisibilityTextSaved
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            val savedState = state as VisibilityTextSaved
            super.onRestoreInstanceState(savedState.superState)
            update(savedState.restore())
        }

    override fun update(state: VisibilityTextState) {
        this.state = state
        state.update(this)
    }

    override fun update(visibility: Int) {
        setVisibility(visibility)
    }
}

interface UpdateVisibility {
    fun update(state: VisibilityTextState)
    fun update(visibility: Int)

}