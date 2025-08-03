package com.daisybell.expertcoursequizgame.views.stats

import java.io.Serializable

interface StatsUiState : Serializable {
    fun show(statsTextView: UpdateStats)

    data class Base(
        private val correct: Int,
        private val incorrect: Int
    ) : StatsUiState {
        override fun show(statsTextView: UpdateStats) {
            statsTextView.updateProperties(correct, incorrect)
        }
    }
}