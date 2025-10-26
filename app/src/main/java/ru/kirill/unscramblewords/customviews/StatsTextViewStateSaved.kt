package ru.kirill.unscramblewords.customviews

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import kotlin.jvm.java

class StatsTextViewStateSaved : View.BaseSavedState {

    private lateinit var state: StatsTextViewState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(StatsTextViewState::class.java.classLoader, StatsTextViewState::class.java) as StatsTextViewState
        } else {
            parcelIn.readSerializable() as StatsTextViewState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore() = state

    fun save(state: StatsTextViewState) {
        this.state = state
    }


    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<StatsTextViewStateSaved> {
        override fun createFromParcel(parcel: Parcel): StatsTextViewStateSaved {
            return StatsTextViewStateSaved(parcel)
        }

        override fun newArray(size: Int): Array<StatsTextViewStateSaved?> {
            return arrayOfNulls(size)
        }
    }
}