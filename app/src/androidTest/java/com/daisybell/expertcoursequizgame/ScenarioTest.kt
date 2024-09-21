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
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerQuestionState)

        gamePage.clickFirstChoice()
        activityScenarioRule.doWithRecreate(gamePage::assertChoiceMadeStateFirstChoice)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerCheckedStateFirstChoice)
    }

    /**
     * QGTC-02
     */
    @Test
    fun caseNumber2() {
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerQuestionState)

        gamePage.clickFirstChoice()
        activityScenarioRule.doWithRecreate(gamePage::assertChoiceMadeStateFirstChoice)

        gamePage.clickSecondChoice()
        activityScenarioRule.doWithRecreate(gamePage::assertChoiceMadeStateSecondChoice)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerCheckedStateFirstCorrectSecondIncorrect)

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color in the grass?",
            choices = listOf("green", "blue", "yellow", "red")
        )

        activityScenarioRule.doWithRecreate(gamePage::assertAnswerQuestionState)
    }

    private fun ActivityScenarioRule<*>.doWithRecreate(block: () -> Unit) {
        block.invoke()
        scenario.recreate()
        block.invoke()
    }
}