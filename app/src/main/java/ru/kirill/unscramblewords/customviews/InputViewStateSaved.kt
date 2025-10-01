package ru.kirill.unscramblewords.customviews

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import java.io.Serializable

class InputViewStateSaved : View.BaseSavedState {

    private lateinit var state: InputViewState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(InputViewState::class.java.classLoader, InputViewState::class.java) as InputViewState
        } else {
            parcelIn.readSerializable() as InputViewState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore() = state

    fun save(state: InputViewState) {
        this.state = state
    }


    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<InputViewStateSaved> {
        override fun createFromParcel(parcel: Parcel): InputViewStateSaved {
            return InputViewStateSaved(parcel)
        }

        override fun newArray(size: Int): Array<InputViewStateSaved?> {
            return arrayOfNulls(size)
        }
    }
}

data class InputViewState(private val inputText: String) : Serializable {
    fun update(input: InputView) {
        input.update(inputText, true)
    }


}
