package ru.kirill.unscramblewords

import android.content.Context
import android.os.Parcelable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout

class InputView : FrameLayout, UpdateInput {
    private lateinit var state: InputViewState
    public val binding =
        ru.kirill.unscramblewords.databinding.InputBinding.inflate(LayoutInflater.from(context),
            this,
            true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    
    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val InputViewStateSaved = InputViewStateSaved(it)
            InputViewStateSaved.save(state)
            return InputViewStateSaved
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as InputViewStateSaved
        super.onRestoreInstanceState(savedState.superState)
        update(savedState.restore())
    }

    override fun update(updateInput: InputViewState) {
        this.state = updateInput
        state.update(this)
    }

    override fun update(inputText: String, isEnabled: Boolean) {
        if (binding.textInputEditText.text.toString() != inputText) {
            binding.textInputEditText.setText(inputText)
        }
        binding.textInputEditText.isEnabled = isEnabled
    }

    fun removeTextChangedListener(textWatcher: TextWatcher) {
        binding.textInputEditText.removeTextChangedListener(textWatcher)
    }

    fun addTextChangedListener(textWatcher: TextWatcher) {
        binding.textInputEditText.addTextChangedListener(textWatcher)
    }
}

interface UpdateInput {
    fun update(updateInput: InputViewState)
    fun update(
        inputText: String,
        isEnabled: Boolean
    )

}
