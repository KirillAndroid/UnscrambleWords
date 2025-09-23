package ru.kirill.unscramblewords

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import java.io.Serializable

class VisibilityStateSaved : View.BaseSavedState {

    private lateinit var state: VisibilityButtonState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        Log.d("dd33", "constructor inside choiceButtonSaveState")
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(VisibilityButtonState::class.java.classLoader, VisibilityButtonState::class.java) as VisibilityButtonState
        } else {
            parcelIn.readSerializable() as VisibilityButtonState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore() = state

    fun save(state: VisibilityButtonState) {
        this.state = state
    }


    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<VisibilityStateSaved> {
        override fun createFromParcel(parcel: Parcel): VisibilityStateSaved {
            return VisibilityStateSaved(parcel)
        }

        override fun newArray(size: Int): Array<VisibilityStateSaved?> {
            return arrayOfNulls(size)
        }
    }
}

data class VisibilityButtonState(private val visibility: Int, private val isEnabled: Boolean) : Serializable {
    fun update(button: VisibilityButton) {
        button.update(visibility, isEnabled)
    }
}