package com.daisybell.expertcoursequizgame.views.visiblebutton

import android.view.View
import java.io.Serializable

interface VisibilityUiState : Serializable {

    fun update(updateVisibility: UpdateVisibility)

    abstract class Abstract(private val visibility: Int) : VisibilityUiState {

        override fun update(updateVisibility: UpdateVisibility) =
            updateVisibility.update(visibility)
    }

    class Visible : Abstract(View.VISIBLE)
    class Invisible : Abstract(View.INVISIBLE)
    class Gone : Abstract(View.GONE)

}