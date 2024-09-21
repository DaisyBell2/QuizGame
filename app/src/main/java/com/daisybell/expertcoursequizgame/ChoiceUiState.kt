package com.daisybell.expertcoursequizgame

import com.daisybell.expertcoursequizgame.views.choice.UpdateChoiceButton
import java.io.Serializable

interface ChoiceUiState : Serializable {

    fun update(updateChoiceButton: UpdateChoiceButton)

    abstract class Abstract(
        private val color: String,
        private val clickable: Boolean,
        private val enabled: Boolean
    ) : ChoiceUiState {

        override fun update(updateChoiceButton: UpdateChoiceButton) {
            updateChoiceButton.update(color, clickable, enabled)
        }
    }

    object NotAvailableToChoose :
        Abstract(color = "#757575", clickable = false, enabled = false)

    data class Initial(private val text: String) :
        Abstract(color = "#61A0FF", clickable = true, enabled = true) {
        override fun update(updateChoiceButton: UpdateChoiceButton) {
            super.update(updateChoiceButton)
            updateChoiceButton.update(text)
        }
    }

    object AvailableToChoose :
        Abstract(color = "#61A0FF", clickable = true, enabled = true)

    object Correct :
        Abstract(color = "#32DB17", clickable = false, enabled = true)

    object Incorrect :
        Abstract(color = "#EE4B4B", clickable = false, enabled = true)

}