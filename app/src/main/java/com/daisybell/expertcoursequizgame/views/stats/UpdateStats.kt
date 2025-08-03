package com.daisybell.expertcoursequizgame.views.stats

interface UpdateStats {

    fun updateUiState(state: StatsUiState)

    fun updateProperties(correct: Int, incorrect: Int)
}