package ru.kirill.unscramblewords

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet

class VisibilityButton : androidx.appcompat.widget.AppCompatButton, UpdateButtonVisibility {
    private lateinit var state: VisibilityButtonState

    constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

        override fun onSaveInstanceState(): Parcelable? {
            return super.onSaveInstanceState()?.let {
                val VisibilitySavedState = VisibilityStateSaved(it)
                VisibilitySavedState.save(state)
                return VisibilitySavedState
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            val savedState = state as VisibilityStateSaved
            super.onRestoreInstanceState(savedState.superState)
            update(savedState.restore())
        }

    override fun update(restore: VisibilityButtonState) {
        state = restore
        restore.update(this)
    }

    override fun update(visibility: Int, isEnabled: Boolean) {
        this.isEnabled = isEnabled
        setVisibility(visibility)
    }
}

interface UpdateButtonVisibility {
    fun update(restore: VisibilityButtonState)
    fun update(visibility: Int, isEnabled: Boolean)
}
