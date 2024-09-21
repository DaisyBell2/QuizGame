package com.daisybell.expertcoursequizgame.views.choice

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import com.daisybell.expertcoursequizgame.ChoiceUiState

/**
 * @author DaisyBell on 21.09.2024
 */
class ChoiceButtonSavedState : View.BaseSavedState {

    private lateinit var state: ChoiceUiState

    constructor(superState: Parcelable) : super(superState)

    internal constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                ChoiceUiState::class.java.classLoader,
                ChoiceUiState::class.java
            ) as ChoiceUiState
        } else {
            parcelIn.readSerializable() as ChoiceUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): ChoiceUiState = state

    fun save(uiState: ChoiceUiState) {
        state = uiState
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ChoiceButtonSavedState> {
        override fun createFromParcel(parcel: Parcel): ChoiceButtonSavedState =
            ChoiceButtonSavedState(parcel)

        override fun newArray(size: Int): Array<ChoiceButtonSavedState?> =
            arrayOfNulls(size)

    }
}