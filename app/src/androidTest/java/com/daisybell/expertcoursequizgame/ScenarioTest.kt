package com.daisybell.expertcoursequizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.daisybell.expertcoursequizgame.game.GamePage
import com.daisybell.expertcoursequizgame.gameover.GameOverPage
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

    /**
     * QGTC-03
     */
    @Test
    fun caseNumber3() {
        //region 2 incorrects
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerQuestionState)

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

        gamePage.clickSecondChoice()
        activityScenarioRule.doWithRecreate(gamePage::assertChoiceMadeStateSecondChoice)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerCheckedStateFirstCorrectSecondIncorrect)

        gamePage.clickNext()
        gamePage.assertDoesNotExist()

        var gameOverPage = GameOverPage(incorrects = 2, corrects = 0)
        activityScenarioRule.doWithRecreate(gameOverPage::assertInitialState)

        gameOverPage.clickNewGame()
        gameOverPage.assertDoesNotExist()

        //endregion

        //region 1 correct 1 incorrect
        gamePage = GamePage(
            question = "What color in the blood?",
            choices = listOf("red", "blue", "yellow", "green")
        )

        activityScenarioRule.doWithRecreate(gamePage::assertAnswerQuestionState)

        gamePage.clickSecondChoice()
        activityScenarioRule.doWithRecreate(gamePage::assertChoiceMadeStateSecondChoice)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerCheckedStateFirstCorrectSecondIncorrect)

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color in the sun?",
            choices = listOf("yellow", "blue", "green", "red")
        )

        activityScenarioRule.doWithRecreate(gamePage::assertAnswerQuestionState)

        gamePage.clickFirstChoice()
        activityScenarioRule.doWithRecreate(gamePage::assertChoiceMadeStateFirstChoice)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerCheckedStateFirstChoice)

        gamePage.clickNext()
        gamePage.assertDoesNotExist()

        gameOverPage = GameOverPage(incorrects = 1, corrects = 1)
        activityScenarioRule.doWithRecreate(gameOverPage::assertInitialState)

        gameOverPage.clickNewGame()
        gameOverPage.assertDoesNotExist()

        //endregion

        //region 2 corrects
        gamePage = GamePage(
            question = "What color in the crocodile?",
            choices = listOf("green", "blue", "yellow", "red")
        )

        activityScenarioRule.doWithRecreate(gamePage::assertAnswerQuestionState)

        gamePage.clickFirstChoice()
        activityScenarioRule.doWithRecreate(gamePage::assertChoiceMadeStateFirstChoice)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerCheckedStateFirstChoice)

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color in the christmas tree?",
            choices = listOf("green", "blue", "yellow", "red")
        )

        activityScenarioRule.doWithRecreate(gamePage::assertAnswerQuestionState)

        gamePage.clickFirstChoice()
        activityScenarioRule.doWithRecreate(gamePage::assertChoiceMadeStateFirstChoice)

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertAnswerCheckedStateFirstChoice)

        gamePage.clickNext()
        gamePage.assertDoesNotExist()

        gameOverPage = GameOverPage(incorrects = 0, corrects = 2)
        activityScenarioRule.doWithRecreate(gameOverPage::assertInitialState)
        //endregion
    }

    private fun ActivityScenarioRule<*>.doWithRecreate(block: () -> Unit) {
        block.invoke()
        scenario.recreate()
        block.invoke()
    }
}