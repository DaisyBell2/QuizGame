package com.daisybell.expertcoursequizgame

class GameViewModel(private val repository: GameRepository) {

    fun init(firstRun: Boolean = true): GameUiState =
        if (firstRun) {
            val data = repository.questionAndChoices()
            GameUiState.AnswerQuestion(
                question = data.question,
                choices = data.choices
            )
        } else {
            GameUiState.Empty
        }

    fun chooseFirst(): GameUiState {
        repository.saveUserChoice(0)
        return GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
    }

    fun chooseSecond(): GameUiState {
        repository.saveUserChoice(1)
        return GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
    }

    fun chooseThird(): GameUiState {
        repository.saveUserChoice(2)
        return GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
            )
        )
    }

    fun chooseFourth(): GameUiState {
        repository.saveUserChoice(3)
        return GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
            )
        )
    }

    fun check(): GameUiState {
        val indexes = repository.check()
        val data = repository.questionAndChoices()

        return GameUiState.AnswerChecked(
            choices = data.choices.mapIndexed { index, _ ->
                if (indexes.correctIndex == index) {
                    ChoiceUiState.Correct
                } else if (indexes.userChoiceIndex == index) {
                    ChoiceUiState.Incorrect
                } else {
                    ChoiceUiState.NotAvailableToChoose
                }
            }
        )
    }

    fun next(): GameUiState {
        repository.next()
        return init()
    }

}
