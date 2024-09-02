package com.daisybell.expertcoursequizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.daisybell.expertcoursequizgame.game.GamePage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup() {
        gamePage = GamePage(
            question = "What color in the sky?",
            choices = listOf("blue", "green", "red", "yellow")
        )
    }

    /**
     * QGTC-01
     */
    @Test
    fun caseNumber1() {
        gamePage.assertAnswerQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertChoiceMadeStateFirstChoice()
        activityScenarioRule.scenario.recreate()
        gamePage.assertChoiceMadeStateFirstChoice()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstChoice()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstChoice()
    }

    /**
     * QGTC-02
     */
    @Test
    fun caseNumber2() {
        gamePage.assertAnswerQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertChoiceMadeStateFirstChoice()
        activityScenarioRule.scenario.recreate()
        gamePage.assertChoiceMadeStateFirstChoice()

        gamePage.clickSecondChoice()
        gamePage.assertChoiceMadeStateSecondChoice()
        activityScenarioRule.scenario.recreate()
        gamePage.assertChoiceMadeStateSecondChoice()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstCorrectSecondIncorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstCorrectSecondIncorrect()

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color in the grass?",
            choices = listOf("green", "blue", "yellow", "red")
        )

        gamePage.assertAnswerQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerQuestionState()
    }
}