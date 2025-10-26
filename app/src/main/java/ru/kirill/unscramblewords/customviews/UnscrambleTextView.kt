package ru.kirill.unscramblewords.customviews

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class UnscrambleTextView : AppCompatTextView, UpdateText {

    constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun update(text: String) {
        this.text = text
    }

    override fun getFreezesText(): Boolean {
        return true
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
    }
}

interface UpdateText {
    fun update(text: String)

}
