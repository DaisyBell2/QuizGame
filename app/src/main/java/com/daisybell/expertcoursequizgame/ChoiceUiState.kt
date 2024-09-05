package com.daisybell.expertcoursequizgame

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton
import java.io.Serializable

interface ChoiceUiState : Serializable {

    fun update(button: AppCompatButton)

    abstract class Abstract(
        private val textButton: String,
        private val color: String,
        private val clickable: Boolean,
        private val enabled: Boolean
    ) : ChoiceUiState {
        override fun update(button: AppCompatButton) = with(button) {
            text = textButton
            setBackgroundColor(Color.parseColor(color))
            isClickable = clickable
            isEnabled = enabled
        }
    }

    data class NotAvailableToChoose(private val text: String) :
        Abstract(text, color = "#757575", clickable = false, enabled = false)

    data class AvailableToChoose(private val text: String) :
        Abstract(text, color = "#61A0FF", clickable = true, enabled = true)

    data class Correct(private val text: String) :
        Abstract(text, color = "#32DB17", clickable = false, enabled = true)

    data class Incorrect(private val text: String) :
        Abstract(text, color = "#EE4B4B", clickable = false, enabled = true)

}