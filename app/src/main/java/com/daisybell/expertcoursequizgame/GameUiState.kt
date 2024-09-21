package com.daisybell.expertcoursequizgame

import com.daisybell.expertcoursequizgame.views.choice.UpdateChoiceButton
import com.daisybell.expertcoursequizgame.views.question.UpdateText
import com.daisybell.expertcoursequizgame.views.visiblebutton.UpdateVisibility
import com.daisybell.expertcoursequizgame.views.visiblebutton.VisibilityUiState
import java.io.Serializable

interface GameUiState : Serializable {

    fun update(
        questionTextView: UpdateText,
        firstChoiceButton: UpdateChoiceButton,
        secondChoiceButton: UpdateChoiceButton,
        thirdChoiceButton: UpdateChoiceButton,
        fourthChoiceButton: UpdateChoiceButton,
        checkButton: UpdateVisibility,
        nextButton: UpdateVisibility,
    )

    data class AnswerQuestion(
        private val question: String,
        private val choices: List<String>
    ) : GameUiState {

        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            checkButton: UpdateVisibility,
            nextButton: UpdateVisibility
        ) {
            questionTextView.update(question)
            firstChoiceButton.update(ChoiceUiState.Initial(choices[0]))
            secondChoiceButton.update(ChoiceUiState.Initial(choices[1]))
            thirdChoiceButton.update(ChoiceUiState.Initial(choices[2]))
            fourthChoiceButton.update(ChoiceUiState.Initial(choices[3]))
            checkButton.update(VisibilityUiState.Invisible())
            nextButton.update(VisibilityUiState.Gone())
        }
    }

    data class ChoiceMade(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {

        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            checkButton: UpdateVisibility,
            nextButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            fourthChoiceButton.update(choices[3])
            checkButton.update(VisibilityUiState.Visible())
        }
    }

    data class AnswerChecked(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {

        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            checkButton: UpdateVisibility,
            nextButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            fourthChoiceButton.update(choices[3])
            checkButton.update(VisibilityUiState.Invisible())
            nextButton.update(VisibilityUiState.Visible())
        }
    }

    object Empty : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            checkButton: UpdateVisibility,
            nextButton: UpdateVisibility
        ) = Unit
    }

}