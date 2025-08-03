package com.daisybell.expertcoursequizgame.views.stats

/**
 * @author DaisyBell on 13.07.2025
 */
import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.daisybell.expertcoursequizgame.R

class StatsTextView : AppCompatTextView, UpdateStats {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var uiState: StatsUiState

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = StatsTextViewSavedState(it)
            savedState.save(uiState)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as StatsTextViewSavedState
        super.onRestoreInstanceState(restoredState.superState)
        updateUiState(restoredState.restore())
    }

    override fun updateUiState(state: StatsUiState) {
        uiState = state
        state.show(this)
    }

    override fun updateProperties(correct: Int, incorrect: Int) {
        text = resources.getString(R.string.stats, correct, incorrect)
    }
}