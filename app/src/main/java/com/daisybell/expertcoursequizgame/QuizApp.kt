package com.daisybell.expertcoursequizgame

import android.app.Application
import android.content.Context

/**
 * @author DaisyBell on 05.09.2024
 */
class QuizApp : Application() {

    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                index = IntCache.Base(sharedPreferences, "indexKey", 0),
                userChoiceIndex = IntCache.Base(sharedPreferences, "userChoiceIndexKey", -1),
            )
        )
    }
}