package ru.kirill.unscramblewords

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import java.io.Serializable

class VisibilityTextSaved : View.BaseSavedState {

    private lateinit var state: VisibilityTextState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(VisibilityTextState::class.java.classLoader, VisibilityTextState::class.java) as VisibilityTextState
        } else {
            parcelIn.readSerializable() as VisibilityTextState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore() = state

    fun save(state: VisibilityTextState) {
        this.state = state
    }


    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<VisibilityTextSaved> {
        override fun createFromParcel(parcel: Parcel): VisibilityTextSaved {
            return VisibilityTextSaved(parcel)
        }

        override fun newArray(size: Int): Array<VisibilityTextSaved?> {
            return arrayOfNulls(size)
        }
    }
}

data class VisibilityTextState(private val visibility: Int, private val isEnabled: Boolean) : Serializable {
    fun update(text: VisibilityText) {
        text.update(visibility)
        text.isEnabled = isEnabled
    }
}